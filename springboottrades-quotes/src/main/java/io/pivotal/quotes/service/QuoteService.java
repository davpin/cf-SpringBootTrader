package io.pivotal.quotes.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.pivotal.quotes.domain.CompanyInfo;
import io.pivotal.quotes.domain.Quote;
import io.pivotal.quotes.domain.YahooQuoteResponse;
import io.pivotal.quotes.domain.QuoteMapper;
import io.pivotal.quotes.exception.SymbolNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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

	// @Value("${pivotal.quotes.yahoo_query}")
	// protected String QID =
	// "select * from yahoo.finance.quotes where symbol in ";

	@Value("${pivotal.quotes.yahoo_env}")
	protected String ENV = "http://datatables.org/alltables.env";

	public static final String FMT = "json";

	private static final Logger logger = LoggerFactory
			.getLogger(QuoteService.class);

	/*
	 * cannot autowire as don't want ribbon here.
	 */
	private RestTemplate restTemplate = getRestTemplate();
	
	private static RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		for (HttpMessageConverter<?> conv : template.getMessageConverters() ) {
			if (conv instanceof MappingJackson2HttpMessageConverter ) {
				//found
				MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) conv;
				converter.getObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			}
		}
		return template;
	}

	/**
	 * Retrieves an up to date quote for the given symbol.
	 * 
	 * @param symbol
	 *            The symbol to retrieve the quote for.
	 * @return The quote object or null if not found.
	 * @throws SymbolNotFoundException
	 */
	@HystrixCommand(fallbackMethod = "getQuoteFallback")
	public Quote getQuote(String symbol) throws SymbolNotFoundException {
		logger.debug("QuoteService.getQuote: retrieving quote for: " + symbol);
		Map<String, String> params = new HashMap<String, String>();
		params.put("symbol", symbol);

		Quote quote = restTemplate.getForObject(quote_url, Quote.class, params);
		logger.debug("QuoteService.getQuote: retrieved quote: " + quote);

		if (quote.getSymbol() == null) {
			throw new SymbolNotFoundException("Symbol not found: " + symbol);
		}
		return quote;
	}

	@SuppressWarnings("unused")
	private Quote getQuoteFallback(String symbol)
			throws SymbolNotFoundException {
		logger.debug("QuoteService.getQuoteFallback: circuit opened for symbol: "
				+ symbol);
		throw new RuntimeException("Quote service unavailable.");
	}

	/**
	 * Retrieves a list of CompanyInfor objects. Given the name parameters, the
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
		logger.debug("QuoteService.getCompanyInfo: retrieving info for: "
				+ name);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		CompanyInfo[] companies = restTemplate.getForObject(company_url,
				CompanyInfo[].class, params);
		logger.debug("QuoteService.getCompanyInfo: retrieved info: "
				+ companies);
		return Arrays.asList(companies);
	}

	/**
	 * Retrieve multiple quotes at once.
	 * 
	 * @param symbols
	 *            comma delimited list of symbols.
	 * @return a list of quotes.
	 */
	public List<Quote> getQuotes(String symbols) {
		logger.debug("retrieving multiple quotes for: "
				+ symbols);
		YahooQuoteResponse response = restTemplate.getForObject(yahoo_url,
				YahooQuoteResponse.class, symbols, FMT, ENV);
		logger.debug("Got response: " + response);
		List<Quote> quotes = response
				.getResults()
				.getQuoteList()
				.getQuote()
				.stream()
				.map(yQuote -> QuoteMapper.INSTANCE.mapFromYahooQuote(yQuote,
						response.getResults().getCreated()))
				.collect(Collectors.toList());
		return quotes;
	}


	@SuppressWarnings("unused")
	private Quote getCompanyInfoFallback(String symbol)
			throws SymbolNotFoundException {
		logger.debug("QuoteService.getCompanyInfoFallback: circuit opened for symbol: "
				+ symbol);
		throw new RuntimeException("Company Info service unavailable.");
	}
}
