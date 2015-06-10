package io.pivotal.web.service;

import java.util.Map;

import io.pivotal.web.domain.Account;
import io.pivotal.web.domain.AuthenticationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	
	public void createAccount(Account account) {
		logger.debug("Saving account with userId: " + account.getUserid());
		String status = restTemplate.postForObject("http://accounts/account/", account, String.class);
		logger.info("Status from registering account for "+ account.getUserid()+ " is " + status);
	}
	
	public Map<String,Object> login(AuthenticationRequest request){
		logger.debug("logging in with userId:" + request.getUsername());
		Map<String,Object> result = (Map<String, Object>) restTemplate.postForObject("http://accounts/login/".toString(), request, Map.class);
		return result;
	}
	
	//TODO: change to /account/{user}
	public Account getAccount(String user) {
		logger.debug("Looking for account with userId: " + user);
		
	    Account account = restTemplate.getForObject("http://accounts/account/?name={user}", Account.class, user);
	    logger.debug("Got Account: " + account);
	    return account;
	}
	
	public void logout(String user) {
		logger.debug("logging out account with userId: " + user);
		
	    ResponseEntity<?> response = restTemplate.getForEntity("http://accounts/logout/{user}", String.class, user);
	    logger.debug("Logout response: " + response.getStatusCode());
	}
	
}
