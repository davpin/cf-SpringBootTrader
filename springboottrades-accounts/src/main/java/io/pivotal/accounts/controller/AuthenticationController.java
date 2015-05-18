package io.pivotal.accounts.controller;

import io.pivotal.accounts.domain.AuthenticationRequest;
import io.pivotal.accounts.service.AccountService;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AccountService service;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public Map<String, Object> login(@RequestBody AuthenticationRequest authenticationRequest) {
		Map<String, Object> authenticationResponse = this.service.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		return authenticationResponse;// authToken and accountId;
	}

	@RequestMapping(value = "/logout/{user}", method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void logout(@PathVariable("user") final String userId) {
		this.service.logout(userId);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.METHOD_NOT_ALLOWED )
	public void get() {
		
	}
}
