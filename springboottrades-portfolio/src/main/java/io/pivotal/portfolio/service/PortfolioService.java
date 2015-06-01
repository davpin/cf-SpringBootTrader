package io.pivotal.portfolio.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
	private static final Logger logger = LoggerFactory
			.getLogger(PortfolioService.class);

	@Autowired
	OrderRepository repository;
	
	@Autowired
	@LoadBalanced
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
		logger.debug("Getting portfolio for accountId: " + accountId);
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
		logger.debug("Portfolio: " + portfolio);
		return portfolio;
	}
	
	private void refreshHolding(Holding holding) {
		Quote quote = getQuote(holding.getSymbol());
		holding.setCurrentValue(new BigDecimal(quote.getLastPrice()));
	}
	
	private Quote getQuote(String symbol) {
		logger.debug("Fetching quote: " + symbol);
		Quote quote = restTemplate.getForObject("http://quotes/quote/{symbol}", Quote.class, symbol);
		return quote;
	}
	
	@Transactional
	public Order addOrder(Order order) {
		
		if (order.getOrderFee() == null) {
			order.setOrderFee(new BigDecimal(10.50));
		}
		if (order.getOrderType() == OrderType.BUY) {
			double amount = order.getQuantity()*order.getPrice().doubleValue()+order.getOrderFee().doubleValue();
			ResponseEntity<Double>  result= restTemplate.getForEntity("http://accounts/accounts/{userid}/decreaseBalance/{amount}", Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				return repository.save(order);
			} else {
				//TODO: throw exception - not enough funds!
				return null;
			}
		} else {
			double amount = order.getQuantity()*order.getPrice().doubleValue()-order.getOrderFee().doubleValue();
			ResponseEntity<Double>  result= restTemplate.getForEntity("http://accounts/accounts/{userid}/increaseBalance/{amount}", Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				return repository.save(order);
			} else {
				//TODO: throw exception - negative value???
				return null;
			}
		}
		
			
	}
}
