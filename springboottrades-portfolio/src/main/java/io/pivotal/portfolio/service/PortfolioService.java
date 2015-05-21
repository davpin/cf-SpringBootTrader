package io.pivotal.portfolio.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.util.UriInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import io.pivotal.portfolio.domain.Holding;
import io.pivotal.portfolio.domain.Order;
import io.pivotal.portfolio.domain.OrderType;
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
	@Autowired @Qualifier("accountService") UriInfo accountService;
	
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * Retrieves the portfolio for the given accountId. 
	 * 
	 * @param accountId The account id to retrieve for.
	 * @return The portfolio.
	 */
	public Portfolio getPortfolio(String accountId) {
		/*
		 * Retrieve all orders for accounts id and build portfolio.
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
		portfolio.refreshTotalValue();
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
		
		if (order.getOrderFee() == null) {
			order.setOrderFee(new BigDecimal(10.50));
		}
		if (order.getOrderType() == OrderType.BUY) {
			double amount = order.getQuantity()*order.getPrice().doubleValue()+order.getOrderFee().doubleValue();
			ResponseEntity<Double>  result= restTemplate.getForEntity(accountService.getUri().toString()+"/accounts/{userid}/decreaseBalance/{amount}", Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				return repository.save(order);
			} else {
				//TODO: throw exception - not enough funds!
				return null;
			}
		} else {
			double amount = order.getQuantity()*order.getPrice().doubleValue()-order.getOrderFee().doubleValue();
			ResponseEntity<Double>  result= restTemplate.getForEntity(accountService.getUri().toString()+"/accounts/{userid}/increaseBalance/{amount}", Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				return repository.save(order);
			} else {
				//TODO: throw exception - negative value???
				return null;
			}
		}
		
			
	}
}
