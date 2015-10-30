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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableScheduling
@RefreshScope
public class MarketService {
	private static final Logger logger = LoggerFactory
			.getLogger(MarketService.class);
	@Value("${pivotal.summary.quotes:3}")
	private Integer numberOfQuotes;
	
	//10 minutes in milliseconds
	//@Value("${pivotal.summary.refresh:600000l}")
	// has to be final to be used in scheduled annotation - TODO, how to change it dynamically?
	private final static long REFRESH_PERIOD = 600000l;
	
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	
	//private static List<String> symbolsIT = Arrays.asList("EMC", "ORCL", "IBM", "INTC", "AMD", "HPQ", "CSCO", "AAPL");
	//private static List<String> symbolsFS = Arrays.asList("JPM", "C", "MS", "BAC", "GS", "WFC","BK");
	@Value("${pivotal.summary.symbols.it:EMC,IBM,VMW}")
	private String symbolsIT;
    @Value("${pivotal.summary.symbols.fs:JPM,C,MS}")
	private String symbolsFS;
    
    @Value("${pivotal.quotesService.name}")
	private String quotesService;
	
    @Value("${pivotal.portfolioService.name}")
	private String portfolioService;
    
	private MarketSummary summary = new MarketSummary();
	
	public MarketSummary getMarketSummary() {
		logger.debug("Retrieving Market Summary");
		
		return summary;
	}
	
	@HystrixCommand(fallbackMethod = "getQuoteFallback")
	public Quote getQuote(String symbol) {
		logger.debug("Fetching quote: " + symbol);
		Quote quote = restTemplate.getForObject("https://" + quotesService + "/quote/{symbol}", Quote.class, symbol);
		return quote;
	}
	
	private Quote getQuoteFallback(String symbol) {
		logger.debug("Fetching fallback quote for: " + symbol);
		//Quote quote = restTemplate.getForObject("http://quotes/quote/{symbol}", Quote.class, symbol);
		Quote quote = new Quote();
		quote.setSymbol(symbol);
		quote.setStatus("FAILED");
		return quote;
	}
	@HystrixCommand(fallbackMethod = "getCompaniesFallback")
	public List<CompanyInfo> getCompanies(String name) {
		logger.debug("Fetching companies with name or symbol matching: " + name);
		CompanyInfo[] infos = restTemplate.getForObject("https://" + quotesService + "/company/{name}", CompanyInfo[].class, name);
		return Arrays.asList(infos);
	}
	private List<CompanyInfo> getCompaniesFallback(String name) {
		List<CompanyInfo> infos = new ArrayList<>();
		return infos;
	}
	
	
	
	public Order sendOrder(Order order ) throws OrderNotSavedException{
		logger.debug("send order: " + order);
		
		//check result of http request to ensure its ok.
		
		ResponseEntity<Order>  result = restTemplate.postForEntity("https://" + portfolioService + "/portfolio/{accountId}", order, Order.class, order.getAccountId());
		if (result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new OrderNotSavedException("Could not save the order");
		}
		logger.debug("Order saved:: " + result.getBody());
		return result.getBody();
	}
	@HystrixCommand(fallbackMethod = "getPortfolioFallback")
	public Portfolio getPortfolio(String accountId) {
		Portfolio folio = restTemplate.getForObject("https://" + portfolioService + "/portfolio/{accountid}", Portfolio.class, accountId);
		logger.debug("Portfolio received: " + folio);
		return folio;
	}
	
	private Portfolio getPortfolioFallback(String accountId) {
		logger.debug("Portfolio fallback");
		Portfolio folio = new Portfolio();
		folio.setAccountId(accountId);
		return folio;
	}

	@Scheduled(fixedRate = REFRESH_PERIOD)
	protected void retrieveMarketSummary() {
		logger.debug("Scheduled retrieval of Market Summary");
		/*
		 * Sleuth currently doesn't work with parallelStream.
		 * TODO: re-implement parallel calls.
		 */
		//List<Quote> quotesIT = pickRandomThree(Arrays.asList(symbolsIT.split(","))).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		//List<Quote> quotesFS = pickRandomThree(Arrays.asList(symbolsFS.split(","))).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		List<Quote> quotesIT = pickRandomThree(Arrays.asList(symbolsIT.split(","))).stream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		List<Quote> quotesFS = pickRandomThree(Arrays.asList(symbolsFS.split(","))).stream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		summary.setTopGainers(quotesIT);
		summary.setTopLosers(quotesFS);
	}
	
	private List<String> pickRandomThree(List<String> symbols) {
		List<String> list = new ArrayList<>();
		Collections.shuffle(symbols);
	    list = symbols.subList(0, numberOfQuotes);
	    return list;
	}
}
