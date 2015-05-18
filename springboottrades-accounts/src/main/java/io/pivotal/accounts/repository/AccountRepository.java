package io.pivotal.accounts.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.accounts.domain.Account;

public interface AccountRepository extends CrudRepository<Account,Integer> {
	public Account findByUseridAndPasswd(String userId, String passwd);
	public Account findByUserid(String userId);
	public Account findByAuthtoken(String authtoken);
}
