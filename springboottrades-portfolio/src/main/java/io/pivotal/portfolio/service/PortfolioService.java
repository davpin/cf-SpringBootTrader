package io.pivotal.portfolio.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class PortfolioService {
	private static final Logger logger = LoggerFactory
			.getLogger(PortfolioService.class);

	/**
	 * The order repository to store Order objects.
	 */
	@Autowired
	OrderRepository repository;
	
	/**
	 * The service than handles the calls to get quotes.
	 */
	@Autowired
	QuoteRemoteCallService quoteService;
	
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	
	//@Value("${pivotal.quotesService.name}")
	//protected String quotesService;
	
	@Value("${pivotal.accountsService.name}")
	protected String accountsService;
	
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
		Portfolio folio = new Portfolio();
		folio.setAccountId(accountId);
		return createPortfolio(folio, orders);
	}

	/**
	 * Builds a portfolio object with the list of orders.
	 * 
	 * @param portfolio the portfolio object to build.
	 * @param orders the list of orders.
	 * @return the portfolio object
	 */
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
		//portfolio.getHoldings().values().parallelStream().forEach(holding -> refreshHolding(holding));
		/*
		 * Sleuth currently doesn't work with parallelStream.
		 * TODO: re-implement parallel calls.
		 */
		portfolio.getHoldings().values().stream().forEach(holding -> refreshHolding(holding));
		
		portfolio.refreshTotalValue();
		logger.debug("Portfolio: " + portfolio);
		return portfolio;
	}
	
	/**
	 * Calculates the current value of the holding.
	 * 
	 * @param holding the holding to refresh.
	 */
	private void refreshHolding(Holding holding) {
		Quote quote = quoteService.getQuote(holding.getSymbol());
		if (quote.getStatus().equalsIgnoreCase("SUCCESS")) {
			holding.setCurrentValue(new BigDecimal(quote.getLastPrice()));
		}
	}
	
	/**
	 * Add an order to the repository and modify account balance.
	 * 
	 * @param order the order to add.
	 * @return the saved order.
	 */
	@Transactional
	public Order addOrder(Order order) {
		logger.debug("Adding order: " + order);
		if (order.getOrderFee() == null) {
			order.setOrderFee(new BigDecimal(10.50));
			logger.debug("Adding Fee to order: " + order);
		}
		if (order.getOrderType() == OrderType.BUY) {
			double amount = order.getQuantity()*order.getPrice().doubleValue()+order.getOrderFee().doubleValue();
			ResponseEntity<Double>  result= restTemplate.getForEntity("http://" + accountsService + "/accounts/{userid}/decreaseBalance/{amount}", Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				logger.info(String.format("Account funds updated successfully for account: %s and new funds are: %s", order.getAccountId(), result.getBody()));
				return repository.save(order);
			} else {
				//TODO: throw exception - not enough funds!
				logger.warn("PortfolioService:addOrder - decresing balance HTTP not ok: ");
				return null;
			}
		} else {
			double amount = order.getQuantity()*order.getPrice().doubleValue()-order.getOrderFee().doubleValue();
			ResponseEntity<Double>  result= restTemplate.getForEntity("http://" + accountsService + "/accounts/{userid}/increaseBalance/{amount}", Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				logger.info(String.format("Account funds updated successfully for account: %s and new funds are: %s", order.getAccountId(), result.getBody()));
				return repository.save(order);
			} else {
				//TODO: throw exception - negative value???
				logger.warn("PortfolioService:addOrder - increasing balance HTTP not ok: ");
				return null;
			}
		}
		
			
	}
}
