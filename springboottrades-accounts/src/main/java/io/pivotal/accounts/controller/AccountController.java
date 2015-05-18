package io.pivotal.accounts.controller;



import io.pivotal.accounts.domain.Account;
import io.pivotal.accounts.service.AccountService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccountController.class);

	@Autowired
	private AccountService service;

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> find(@PathVariable("id") final Integer id) {

		logger.info("AccountController.find: id=" + id);

		Account accountResponse = this.service.findAccount(id);
		return new ResponseEntity<Account>(accountResponse,
				getNoCacheHeaders(), HttpStatus.OK);

	}
	@RequestMapping(value = "/account/", method = RequestMethod.GET)
	public ResponseEntity<Account> findAccount(@RequestParam(value="name") final String id) {

		logger.info("AccountController.findAccount: id=" + id);

		Account accountResponse = this.service.findAccount(id);
		return new ResponseEntity<Account>(accountResponse,
				getNoCacheHeaders(), HttpStatus.OK);

	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Account accountRequest,
			UriComponentsBuilder builder) {

		logger.debug("AccountController.save: userId="
				+ accountRequest.getUserid());

		Integer accountProfileId = this.service.saveAccount(accountRequest);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(builder.path("/account/{id}")
				.buildAndExpand(accountProfileId).toUri());
		return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> getAccount(@PathVariable("id") final Integer id) {

		logger.debug("AccountController.getAccount: id=" + id);

		Account accountResponse = this.service.findAccount(id);
		return new ResponseEntity<Account>(accountResponse,
				getNoCacheHeaders(), HttpStatus.OK);

	}
	
	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}
}
