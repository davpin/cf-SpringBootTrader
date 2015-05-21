package io.pivotal.portfolio.controller;

import io.pivotal.portfolio.domain.Order;
import io.pivotal.portfolio.domain.Portfolio;
import io.pivotal.portfolio.service.PortfolioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PortfolioController {
	private static final Logger logger = LoggerFactory
			.getLogger(PortfolioController.class);
	
	@Autowired
	private PortfolioService service;

	@RequestMapping(value = "/portfolio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Portfolio> getPortfolio(@PathVariable("id") final String accountId) {
		Portfolio folio = service.getPortfolio(accountId);
		return new ResponseEntity<Portfolio>(folio, getNoCacheHeaders(), HttpStatus.OK);
	}
	
	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}
	@RequestMapping(value = "/portfolio/{id}", method = RequestMethod.POST)
	public ResponseEntity<Order> addOrder(@PathVariable("id") final String accountId, @RequestBody final Order order, UriComponentsBuilder builder) {
		logger.debug("Adding Order: " + order);
		
		//TODO: can do a test to ensure accountId == order.getAccountId();
		
		Order savedOrder = service.addOrder(order);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(builder.path("/portfolio/{id}")
				.buildAndExpand(accountId).toUri());
		logger.debug("Order added: " + savedOrder);
		if (savedOrder != null && savedOrder.getOrderId() != null) {
			return new ResponseEntity<Order>(savedOrder, responseHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Order>(savedOrder, responseHeaders, HttpStatus.BAD_REQUEST);
		}
	}
}
