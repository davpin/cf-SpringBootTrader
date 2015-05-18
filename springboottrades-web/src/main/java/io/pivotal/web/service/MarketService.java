package io.pivotal.web.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.pivotal.web.domain.MarketSummary;
import io.pivotal.web.domain.Quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.util.UriInfo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableScheduling
public class MarketService {
	private static final Logger logger = LoggerFactory
			.getLogger(MarketService.class);
	
	@Autowired @Qualifier("quoteService") UriInfo quoteService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static List<String> symbolsIT = Arrays.asList("EMC", "ORCL", "IBM");
	private static List<String> symbolsFS = Arrays.asList("JPM", "C", "MS");
	
	private MarketSummary summary = new MarketSummary();
	
	public MarketSummary getMarketSummary() {
		logger.debug("Retrieving Market Summary");
		
		return summary;
	}
	
	private Quote getQuote(String symbol) {
		logger.debug("Fetching quote: " + symbol);
		Quote quote = restTemplate.getForObject(quoteService.getUri().toString()+"/quote/{symbol}", Quote.class, symbol);
		return quote;
	}
	//TODO: prime location for a redis/gemfire caching service!
	@Scheduled(fixedRate = 5000000)
	private void retrieveMarketSummary() {
		logger.debug("Scheduled retrieval of Market Summary");
		List<Quote> quotesIT = symbolsIT.parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		List<Quote> quotesFS = symbolsFS.parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		summary.setTopGainers(quotesIT);
		summary.setTopLosers(quotesFS);
	}
	
	
	
	
	
}
