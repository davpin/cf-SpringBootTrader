package io.pivotal.accounts.service;

import io.pivotal.accounts.domain.Account;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface AccountService {

	Account findAccount(Integer id);

	Account findAccount(String id);

	Integer saveAccount(Account accountRequest);

	Map<String, Object> login(String username, String password);

	Account logout(String userId);

    @Cacheable(value = "authorizationCache")
    Account findAccountprofileByAuthtoken(String token);
}
