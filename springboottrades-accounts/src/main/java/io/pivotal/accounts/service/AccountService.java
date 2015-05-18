package io.pivotal.accounts.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.pivotal.accounts.domain.Account;
import io.pivotal.accounts.exception.AuthenticationException;
import io.pivotal.accounts.exception.NoRecordsFoundException;
import io.pivotal.accounts.repository.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private static final Logger logger = LoggerFactory
			.getLogger(AccountService.class);

	@Autowired
	AccountRepository accounts;

	public Account findAccount(Integer id) {

		logger.info("AccountService.findAccount: id=" + id);

		Account account = accounts.findOne(id);
		if (account == null) {
			throw new NoRecordsFoundException();
		}

		logger.info("AccountService.findAccount - after service call. Payload is: "
				+ account);

		return account;
	}

	public Account findAccount(String id) {

		logger.info("AccountService.findAccount: id=" + id);

		Account account = accounts.findByUserid(id);
		if (account == null) {
			logger.info("No records found for id: " + id);
			throw new NoRecordsFoundException();
		}

		logger.info("AccountService.findAccount - after service call. Payload is: "
				+ account);

		return account;
	}

	@Cacheable(value = "authorizationCache")
	public Account findAccountprofileByAuthtoken(String token) {
		if (token == null) {
			logger.error("TradingServiceFacadeImpl.findAccountprofileByAuthtoken(): token is null");
		}
		Account accountProfile = null;
		accountProfile = accounts.findByAuthtoken(token);
		if (accountProfile == null) {
			logger.error("AccountService.findAccountprofileByAuthtoken(): accountProfile is null for token="
					+ token);
			throw new AuthenticationException("Authorization Token not found");
		}

		return accountProfile;
	}

	public Integer saveAccount(Account accountRequest) {

		logger.debug("AccountService.saveAccount:" + accountRequest.toString());
		// need to set some stuff that cannot be null!
		if (accountRequest.getLogincount() == null) {
			accountRequest.setLogincount(0);
		}
		if (accountRequest.getLogoutcount() == null) {
			accountRequest.setLogoutcount(0);
		}

		Account account = accounts.save(accountRequest);

		return account.getId();
	}

	public Map<String, Object> login(String username, String password) {
		logger.debug("login in user: " + username);
		Map<String, Object> loginResponse = null;
		Account account = accounts.findByUseridAndPasswd(username, password);
		if (account != null) {
			logger.debug("Found Account for user: " + username);
			account.setAuthtoken(UUID.randomUUID().toString());
			account.setLogincount(account.getLogincount() + 1);
			account.setLastlogin(new Date());
			account = accounts.save(account); // persist new auth token and last
												// login
			loginResponse = new HashMap<String, Object>();

			loginResponse.put("authToken", account.getAuthtoken());
			loginResponse.put("accountid", account.getId());
			//loginResponse.put("password", account.getPasswd());
			if (logger.isDebugEnabled()) {
				logger.debug("AccountService.login success for " + username
						+ " username::token=" + loginResponse.get("authToken"));
			}
		} else {
			logger.warn("AccountService.login failed to find username="
					+ username + " password=" + password);
			throw new AuthenticationException("Login failed for user: "
					+ username);
		}
		return loginResponse;
	}

	public void logout(String userId) {
		logger.debug("Logging out accoutn with userId: " + userId);
		Account account = accounts.findByUserid(userId);
		if (account != null) {
			account.setAuthtoken(null); // remove token
			account.setLogoutcount(account.getLogoutcount() + 1);
			accounts.save(account);
			logger.debug("Account logged out: " + userId);
		} else {
			logger.warn("Could not find account to logout with userId: " + userId);
		}
	}
}
