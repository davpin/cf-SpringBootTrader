package io.pivotal.quotes.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.pivotal.quotes.domain.*;
import io.pivotal.quotes.exception.SymbolNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A service to retrieve Company and Quote information.
 * 
 * @author David Ferreira Pinto
 *
 */
@Service
@RefreshScope
public class QuoteService {
	@Value("${pivotal.quotes.quotes_url}")
	protected String quote_url;
	@Value("${pivotal.quotes.companies_url}")
	protected String company_url;

	@Value("${pivotal.quotes.yahoo_rest_query}")
	protected String yahoo_url = "https://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.quotes where symbol in ('{symbol}')&format={fmt}&env={env}";

	@Value("${pivotal.quotes.yahoo_env}")
	protected String ENV = "http://datatables.org/alltables.env";

	public static final String FMT = "json";

	private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

	/*
	 * cannot autowire as don't want ribbon here.
	 */
	private RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieve one or more quotes.
     *
     * @param symbols comma delimited list of symbols.
     * @return a list of quotes.
     */
    @HystrixCommand(fallbackMethod = "getMarkitondemandQuotes")
    public List<Quote> getQuotes(String symbols) throws SymbolNotFoundException {
        logger.debug("retrieving quotes for: " + symbols);
        if ( symbols.isEmpty() ) return new ArrayList<>();

        List<YahooQuote> yahooQuotes;
        Date createDate;
        if ( symbols.indexOf( ',' ) == -1 ) {
            YahooQuoteResponse response = restTemplate.getForObject(yahoo_url, YahooQuoteResponse.class, symbols, FMT, ENV);
            logger.debug("Got response: " + response);
            yahooQuotes = Collections.singletonList( response.getResult().getQuote() );
            createDate = response.getResult().getCreated();
        }
        else {
            YahooQuoteResponses responses = restTemplate.getForObject(yahoo_url, YahooQuoteResponses.class, symbols, FMT, ENV);
            logger.debug("Got responses: " + responses);
            yahooQuotes = responses.getResults().getQuoteList().getQuote();
            createDate = responses.getResults().getCreated();
        }

        List<Quote> quotes = yahooQuotes
                .stream()
                .map(yQuote -> QuoteMapper.INSTANCE.mapFromYahooQuote(yQuote, createDate))
                .collect(Collectors.toList());

        for (Quote quote : quotes) {
            if ( quote.getName() == null ) throw new SymbolNotFoundException( quote.getSymbol() + " not found" );
        }
        return quotes;
    }

	/**
	 * Retrieves an up to date quote for the given symbol.
	 * 
	 * @param symbols Array of symbols to retrieve quotes for.
	 * @return The quote object or null if not found.
	 * @throws SymbolNotFoundException
	 */
	@HystrixCommand(fallbackMethod = "getQuotesFallback")
    @SuppressWarnings("unused")
	public List<Quote> getMarkitondemandQuotes(String symbols) throws SymbolNotFoundException {
        List<Quote> result = new ArrayList<>();
        String[] splitSymbols = symbols.split(",");

        for (String symbol : splitSymbols) {
            logger.debug("QuoteService.getQuote: retrieving quote for: " + symbol);
            Map<String, String> params = new HashMap<>();
            params.put("symbol", symbol);

            Quote quote = restTemplate.getForObject(quote_url, Quote.class, params);
            logger.debug("QuoteService.getQuote: retrieved quote: " + quote);
            result.add(quote);

            if (quote.getSymbol() == null) {
                throw new SymbolNotFoundException("Symbol not found: " + symbol);
            }
        }

		return result;
	}

	@SuppressWarnings("unused")
	private List<Quote> getQuotesFallback(String symbols) {
		logger.debug("QuoteService.getQuoteFallback: circuit opened for symbols: " + symbols);
		throw new RuntimeException("Quote service unavailable.");
	}

    /**
     * Retrieves a list of CompanyInfo objects. Given the name parameters, the
     * return list will contain objects that match the search both on company
     * name as well as symbol.
     *
     * @param name
     *            The search parameter for company name or symbol.
     * @return The list of company information.
     */
    @HystrixCommand(fallbackMethod = "getCompanyInfoFallback",
            commandProperties = {
                    @HystrixProperty(name="execution.timeout.enabled", value="false")
            })
    public List<CompanyInfo> getCompanyInfo(String name) {
        logger.debug("QuoteService.getCompanyInfo: retrieving info for: " + name);
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        CompanyInfo[] companies = restTemplate.getForObject(company_url,
                CompanyInfo[].class, params);
        logger.debug("QuoteService.getCompanyInfo: retrieved info: " + Arrays.toString(companies));
        return Arrays.asList(companies);
    }

	@SuppressWarnings("unused")
	private Quote getCompanyInfoFallback(String symbol)
			throws SymbolNotFoundException {
		logger.debug("QuoteService.getCompanyInfoFallback: circuit opened for symbol: "
				+ symbol);
		throw new RuntimeException("Company Info service unavailable.");
	}
}
