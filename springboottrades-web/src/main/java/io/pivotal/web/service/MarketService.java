package io.pivotal.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.pivotal.web.domain.CompanyInfo;
import io.pivotal.web.domain.MarketSummary;
import io.pivotal.web.domain.Order;
import io.pivotal.web.domain.Portfolio;
import io.pivotal.web.domain.Quote;
import io.pivotal.web.exception.OrderNotSavedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.util.UriInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableScheduling
public class MarketService {
	private static final Logger logger = LoggerFactory
			.getLogger(MarketService.class);
	private final static Integer QUOTES_NUMBER = 3;
	
	//10 minutes in milliseconds
	private final static long REFRESH_PERIOD = 600000l;
	
	@Autowired @Qualifier("quoteService") UriInfo quoteService;
	@Autowired @Qualifier("portfolioService") UriInfo portfolioService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static List<String> symbolsIT = Arrays.asList("EMC", "ORCL", "IBM", "INTC", "AMD", "HPQ", "CSCO", "AAPL");
	private static List<String> symbolsFS = Arrays.asList("JPM", "C", "MS", "BAC", "GS", "WFC","BK");
	
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
	
	private List<CompanyInfo> getCompanies(String name) {
		logger.debug("Fetching companies with name or symbol matching: " + name);
		CompanyInfo[] infos = restTemplate.getForObject(quoteService.getUri().toString()+"/company/{name}", CompanyInfo[].class, name);
		return Arrays.asList(infos);
	}
	
	public List<Quote> getQuotes(String companyName) {
		logger.debug("Fetching quotes for companies that have: " + companyName + " in name or symbol");
		List<CompanyInfo> companies = getCompanies(companyName);
		
		//get district companyinfos and get their respective quotes in parallel.
		List<Quote> quotes = companies.stream().collect(Collectors.toCollection(
			      () -> new TreeSet<CompanyInfo>((p1, p2) -> p1.getSymbol().compareTo(p2.getSymbol())) 
				)).parallelStream().map(n -> getQuote(n.getSymbol())).collect(Collectors.toList());
		
		return quotes;
	}
	
	public Order sendOrder(Order order ) throws OrderNotSavedException{
		logger.debug("send order: " + order);
		
		//check result of http request to ensure its ok.
		
		ResponseEntity<Order>  result = restTemplate.postForEntity(portfolioService.getUri().toString()+"/portfolio/{accountId}", order, Order.class, order.getAccountId());
		if (result.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new OrderNotSavedException("Could not save the order");
		}
		
		return result.getBody();
	}
	
	public Portfolio getPortfolio(String accountId) {
		Portfolio folio = restTemplate.getForObject(portfolioService.getUri().toString()+"/portfolio/{accountid}", Portfolio.class, accountId);
		return folio;
	}
	
	//TODO: prime location for a redis/gemfire caching service!
	@Scheduled(fixedRate = REFRESH_PERIOD)
	private void retrieveMarketSummary() {
		logger.debug("Scheduled retrieval of Market Summary");
		List<Quote> quotesIT = pickRandomThree(symbolsIT).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		List<Quote> quotesFS = pickRandomThree(symbolsFS).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		summary.setTopGainers(quotesIT);
		summary.setTopLosers(quotesFS);
	}
	
	private List<String> pickRandomThree(List<String> symbols) {
		List<String> list = new ArrayList<>();
		Collections.shuffle(symbols);
	    list = symbols.subList(0, QUOTES_NUMBER);
	    return list;
	}
}
