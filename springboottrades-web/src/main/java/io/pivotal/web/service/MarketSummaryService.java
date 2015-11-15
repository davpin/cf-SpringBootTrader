package io.pivotal.web.service;

import io.pivotal.web.domain.MarketSummary;
import io.pivotal.web.domain.Quote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class MarketSummaryService {
	private static final Logger logger = LoggerFactory
			.getLogger(MarketSummaryService.class);
	@Value("${pivotal.summary.quotes:3}")
	private Integer numberOfQuotes;
	
	//10 minutes in milliseconds
	@Value("${pivotal.summary.refresh:600000}")
	private final static String refresh_period = "600000";
	
	//private static List<String> symbolsIT = Arrays.asList("EMC", "ORCL", "IBM", "INTC", "AMD", "HPQ", "CSCO", "AAPL");
	//private static List<String> symbolsFS = Arrays.asList("JPM", "C", "MS", "BAC", "GS", "WFC","BK");
	@Value("${pivotal.summary.symbols.it:EMC,IBM,VMW}")
	private String symbolsIT;
    @Value("${pivotal.summary.symbols.fs:JPM,C,MS}")
	private String symbolsFS;
    
    @Autowired
	private MarketService marketService;
    
	private MarketSummary summary = new MarketSummary();
	
	public MarketSummary getMarketSummary() {
		logger.debug("Retrieving Market Summary: " + summary);
		
		return summary;
	}
	
	@Scheduled(fixedRateString = refresh_period)
	protected void retrieveMarketSummary() {
		logger.debug("Scheduled retrieval of Market Summary");
		/*
		 * Sleuth currently doesn't work with parallelStream.
		 * TODO: re-implement parallel calls.
		 */
		//List<Quote> quotesIT = pickRandomThree(Arrays.asList(symbolsIT.split(","))).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		//List<Quote> quotesFS = pickRandomThree(Arrays.asList(symbolsFS.split(","))).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		
		//List<Quote> quotesFS = pickRandomThree(Arrays.asList(symbolsFS.split(","))).stream().map(symbol -> marketService.getQuote(symbol)).collect(Collectors.toList());
		summary.setTopGainers(getTopThree(symbolsIT));
		summary.setTopLosers(getTopThree(symbolsFS));
	}

	/**
	 * Retrieve the list of top winners/losers.
	 * Currently retrieving list of 3 random.
	 */
	private List<Quote> getTopThree(String symbols) {
		StringBuilder builder = new StringBuilder();
		for(Iterator<String> i = pickRandomThree(Arrays.asList(symbols.split(","))).iterator(); i.hasNext();) {
			builder.append(i.next());
			if (i.hasNext()) {
				builder.append(",");
			}
		}
		return marketService.getMultipleQuotes(builder.toString());
	}
	
	private List<String> pickRandomThree(List<String> symbols) {
		List<String> list = new ArrayList<>();
		Collections.shuffle(symbols);
	    list = symbols.subList(0, numberOfQuotes);
	    return list;
	}
}
