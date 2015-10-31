package io.pivotal.portfolio.service;

import io.pivotal.portfolio.domain.Quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Retrieves quotes from the quote service. Uses hystrix to manage failure.
 * 
 * @author David Ferreira Pinto
 *
 */
@Service
@RefreshScope
public class QuoteRemoteCallService {

	private static final Logger logger = LoggerFactory.getLogger(QuoteRemoteCallService.class);

	@Value("${pivotal.quotesService.name}")
	private String quotesService;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	/**
	 * Retrieve up to date quotes.
	 * 
	 * @param symbol
	 *            the symbol of the quote to fetch.
	 * @return The quote
	 */
	@HystrixCommand(fallbackMethod = "getQuoteFallback")
	public Quote getQuote(String symbol) {
		logger.debug("Fetching quote: " + symbol);
		Quote quote = restTemplate.getForObject("http://" + quotesService + "/quote/{symbol}", Quote.class, symbol);
		return quote;
	}

	/**
	 * Fallback for the quote service.
	 * 
	 * @param symbol
	 *            the symbol of the quote to fetch.
	 * @return Empty quote in FAILED state.
	 */
	@SuppressWarnings("unused")
	private Quote getQuoteFallback(String symbol) {
		logger.debug("Fetching fallback quote for: " + symbol);
		// Quote quote =
		// restTemplate.getForObject("http://quotes/quote/{symbol}",
		// Quote.class, symbol);
		Quote quote = new Quote();
		quote.setSymbol(symbol);
		quote.setStatus("FAILED");
		return quote;
	}
}
