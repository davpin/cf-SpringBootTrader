package io.pivotal.portfolio.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.util.UriInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import io.pivotal.portfolio.domain.Holding;
import io.pivotal.portfolio.domain.Order;
import io.pivotal.portfolio.domain.Portfolio;
import io.pivotal.portfolio.domain.Quote;
import io.pivotal.portfolio.repository.OrderRepository;

/**
 * Manages a portfolio of holdings of stock/shares.
 * 
 * @author David Ferreira Pinto
 *
 */
@Service
public class PortfolioService {

	@Autowired
	OrderRepository repository;
	
	@Autowired @Qualifier("quoteService") UriInfo quoteService;
	
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * Retrieves the portfolio for the given accountId. 
	 * 
	 * @param accountId The account id to retrieve for.
	 * @return The portfolio.
	 */
	public Portfolio getPortfolio(Integer accountId) {
		/*
		 * Retrieve all orders for accounts id and build protfolio.
		 * - for each order create holding.
		 * - for each holding find current price.
		 */
		List<Order> orders = repository.findByAccountId(accountId);
		return createPortfolio(new Portfolio(), orders);
	}

	private Portfolio createPortfolio(Portfolio portfolio, List<Order> orders) {
		//TODO: change to forEach() and maybe in parallel?
		for (Order order: orders) {
			Holding holding = portfolio.getHolding(order.getSymbol());
			if ( holding == null) {
				holding = new Holding();
				holding.setSymbol(order.getSymbol());
				portfolio.addHolding(holding);
			}
			holding.addOrder(order);
		}
		
		// getLatestQuotes in parallel
		portfolio.getHoldings().values().parallelStream().forEach(holding -> refreshHolding(holding));
		return portfolio;
	}
	
	private void refreshHolding(Holding holding) {
		Quote quote = getQuote(holding.getSymbol());
		holding.setCurrentValue(new BigDecimal(quote.getLastPrice()));
	}
	
	private Quote getQuote(String symbol) {
		
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("symbol", symbol);
		Quote quote = restTemplate.getForObject(quoteService.getUri().toString(), Quote.class, params);
		return quote;
	}
	
	@Transactional
	public Order addOrder(Order order) {
		//TODO: check if there are enough funds!
		return repository.save(order);	
	}
}
