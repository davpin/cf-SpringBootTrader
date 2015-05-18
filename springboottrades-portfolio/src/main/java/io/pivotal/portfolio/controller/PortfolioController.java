package io.pivotal.portfolio.controller;

import io.pivotal.portfolio.domain.Order;
import io.pivotal.portfolio.domain.Portfolio;
import io.pivotal.portfolio.service.PortfolioService;

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
	
	@Autowired
	private PortfolioService service;

	@RequestMapping(value = "/portfolio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Portfolio> getPortfolio(@PathVariable("id") final Integer accountId) {
		Portfolio folio = service.getPortfolio(accountId);
		return new ResponseEntity<Portfolio>(folio, getNoCacheHeaders(), HttpStatus.OK);
	}
	
	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}
	@RequestMapping(value = "/porfolio/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> addOrder(@PathVariable("id") final Integer accountId, @RequestBody final Order order, UriComponentsBuilder builder) {
		Order savedOrder = service.addOrder(order);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(builder.path("/portfolio/{id}")
				.buildAndExpand(accountId).toUri());
		if (savedOrder.getOrderId() != null) {
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(responseHeaders, HttpStatus.BAD_REQUEST);
		}
	}
}
