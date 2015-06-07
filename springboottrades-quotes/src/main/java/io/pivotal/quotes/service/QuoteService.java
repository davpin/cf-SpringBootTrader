package io.pivotal.quotes.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.pivotal.quotes.domain.CompanyInfo;
import io.pivotal.quotes.domain.Quote;
import io.pivotal.quotes.exception.SymbolNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * A service to retrieve Company and Quote information.
 * 
 * @author David Ferreira Pinto
 *
 */
@Service
public class QuoteService {

	//TODO: change to get URL from Cloud service?
	//TODO: add hystrix!
	
	private static final String QUOTE_URL = "http://dev.markitondemand.com/Api/v2/Quote/json?symbol={symbol}";
	private static final String COMPANY_URL = "http://dev.markitondemand.com/Api/v2/Lookup/json?input={name}"; 
	
	private static final Logger logger = LoggerFactory
			.getLogger(QuoteService.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	/**
	 * Retrieves an up to date quote for the given symbol.
	 * 
	 * @param symbol The symbol to retrieve the quote for.
	 * @return The quote object or null if not found.
	 * @throws SymbolNotFoundException 
	 */
	public Quote getQuote(String symbol) throws SymbolNotFoundException {
		logger.debug("QuoteService.getQuote: retrieving quote for: " + symbol);
		Map<String, String> params = new HashMap<String, String>();
	    params.put("symbol", symbol);

	    Quote quote = restTemplate.getForObject(QUOTE_URL, Quote.class, params);
        logger.debug("QuoteService.getQuote: retrieved quote: " + quote);
        
        if (quote.getSymbol() ==  null) {
        	throw new SymbolNotFoundException("Symbol not found: " + symbol);
        }
		return quote;
	}
	
	/**
	 * Retrieves a list of CompanyInfor objects.
	 * Given the name parameters, the return list will contain objects that match the search both
	 * on company name as well as symbol.
	 * @param name The search parameter for company name or symbol.
	 * @return The list of company information.
	 */
	public List<CompanyInfo> getCompanyInfo(String name) {
		logger.debug("QuoteService.getCompanyInfo: retrieving info for: " + name);
		Map<String, String> params = new HashMap<String, String>();
	    params.put("name", name);
	    CompanyInfo[] companies = restTemplate.getForObject(COMPANY_URL, CompanyInfo[].class, params);
	    logger.debug("QuoteService.getCompanyInfo: retrieved info: " + companies);
		return Arrays.asList(companies);
	}
}
