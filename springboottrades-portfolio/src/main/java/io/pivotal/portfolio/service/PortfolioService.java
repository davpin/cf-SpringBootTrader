package io.pivotal.portfolio.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
	private static final Logger logger = LoggerFactory.getLogger(PortfolioService.class);

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

	// @Value("${pivotal.quotesService.name}")
	// protected String quotesService;

	@Value("${pivotal.accountsService.name}")
	protected String accountsService;

	/**
	 * Retrieves the portfolio for the given accountId.
	 * 
	 * @param accountId
	 *            The account id to retrieve for.
	 * @return The portfolio.
	 */
	public Portfolio getPortfolio(String accountId) {
		/*
		 * Retrieve all orders for accounts id and build portfolio. - for each
		 * order create holding. - for each holding find current price.
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
	 * @param portfolio
	 *            the portfolio object to build.
	 * @param orders
	 *            the list of orders.
	 * @return the portfolio object
	 */
	private Portfolio createPortfolio(Portfolio portfolio, List<Order> orders) {
		// TODO: change to forEach() and maybe in parallel?
		Set<String> symbols = new HashSet<>();
		Holding holding = null;
		for (Order order : orders) {
			holding = portfolio.getHolding(order.getSymbol());
			if (holding == null) {
				holding = new Holding();
				holding.setSymbol(order.getSymbol());
				portfolio.addHolding(holding);
				symbols.add(order.getSymbol());
			}
			holding.addOrder(order);
		}
		
		List<Quote> quotes = quoteService.getQuotes(symbols);

		for (Quote quote : quotes) {
			portfolio.getHolding(quote.getSymbol()).setCurrentValue(quote.getLastPrice());
		}
		portfolio.refreshTotalValue();
		logger.debug("Portfolio: " + portfolio);
		return portfolio;
	}

	/**
	 * Add an order to the repository and modify account balance.
	 * 
	 * @param order
	 *            the order to add.
	 * @return the saved order.
	 */
	@Transactional
	public Order addOrder(Order order) {
		logger.debug("Adding order: " + order);
		if (order.getOrderFee() == null) {
			order.setOrderFee(Order.DEFAULT_ORDER_FEE);
			logger.debug("Adding Fee to order: " + order);
		}
		if (order.getOrderType() == OrderType.BUY) {
			double amount = order.getQuantity()
					* order.getPrice().doubleValue()
					+ order.getOrderFee().doubleValue();
			ResponseEntity<Double> result = restTemplate.getForEntity("http://"
					+ accountsService
					+ "/accounts/{userid}/decreaseBalance/{amount}",
					Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				logger.info(String
						.format("Account funds updated successfully for account: %s and new funds are: %s",
								order.getAccountId(), result.getBody()));
				return repository.save(order);
			} else {
				// TODO: throw exception - not enough funds!
				// SK - Whats the expected behaviour?
				logger.warn("PortfolioService:addOrder - decresing balance HTTP not ok: ");
				return null;
			}
		} else {
			double amount = order.getQuantity()
					* order.getPrice().doubleValue()
					- order.getOrderFee().doubleValue();
			ResponseEntity<Double> result = restTemplate.getForEntity("http://"
					+ accountsService
					+ "/accounts/{userid}/increaseBalance/{amount}",
					Double.class, order.getAccountId(), amount);
			if (result.getStatusCode() == HttpStatus.OK) {
				logger.info(String
						.format("Account funds updated successfully for account: %s and new funds are: %s",
								order.getAccountId(), result.getBody()));
				return repository.save(order);
			} else {
				// TODO: throw exception - negative value???
				logger.warn("PortfolioService:addOrder - increasing balance HTTP not ok: ");
				return null;
			}
		}
	}
}
