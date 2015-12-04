package io.pivotal.accounts.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.isA;

import java.util.Map;

import io.pivotal.accounts.configuration.ServiceTestConfiguration;
import io.pivotal.accounts.domain.Account;
import io.pivotal.accounts.exception.AuthenticationException;
import io.pivotal.accounts.exception.NoRecordsFoundException;
import io.pivotal.accounts.repository.AccountRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
/**
 * Tests for the AccountService.
 * 
 * @author David Ferreira Pinto
 *
 */
public class AccountServiceTest {
	MockMvc mockMvc;

	@InjectMocks
	AccountService service;
	
	@Mock
	AccountRepository repo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

	/**
	 * test retrieval of account by integer.
	 */
	@Test
	public void doFindAccount() {
		when(repo.findOne(ServiceTestConfiguration.PROFILE_ID)).thenReturn(ServiceTestConfiguration.account());
		assertEquals(service.findAccount(ServiceTestConfiguration.PROFILE_ID).toString(),ServiceTestConfiguration.account().toString());
	}
	/**
	 * test retrieval of account by string - userid.
	 */
	@Test
	public void doFindAccountUserId() {
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(ServiceTestConfiguration.account());
		assertEquals(service.findAccount(ServiceTestConfiguration.USER_ID).toString(),ServiceTestConfiguration.account().toString());
	}
	/**
	 * test retrieval of account by string - userid, with no account found.
	 */
	@Test(expected=NoRecordsFoundException.class)
	public void doFindAccountUserIdNotFound() {
		when(repo.findByUserid(ServiceTestConfiguration.BAD_USER_ID)).thenReturn(null);
		service.findAccount(ServiceTestConfiguration.BAD_USER_ID);
	}
	/**
	 * test retrieval of account not found.
	 */
	@Test(expected=NoRecordsFoundException.class)
	public void doFindNullAccount() {
		when(repo.findOne(999)).thenReturn(null);
		service.findAccount(999);
	}
	
	/**
	 * test retrieval of account by authtoken.
	 */
	@Test
	public void doFindAccountByAuthToken() {
		when(repo.findByAuthtoken(ServiceTestConfiguration.AUTH_TOKEN)).thenReturn(ServiceTestConfiguration.account());
		
		assertEquals(service.findAccountprofileByAuthtoken(ServiceTestConfiguration.AUTH_TOKEN).toString(),ServiceTestConfiguration.account().toString());
	}
	/**
	 * test retrieval of account with no valid authtoken.
	 */
	@Test(expected=AuthenticationException.class)
	public void doFindNullAccountByAuthToken() {
		when(repo.findByAuthtoken("faef8649-280d-4ba4-bdf6-574e758a04a8")).thenReturn(null);
		
		service.findAccountprofileByAuthtoken("faef8649-280d-4ba4-bdf6-574e758a04a8");
	}
	/**
	 * test retrieval of account by authtoken with null.
	 */
	@Test(expected=AuthenticationException.class)
	public void doFindAccountByAuthTokenNull() {
		
		service.findAccountprofileByAuthtoken(null);
	}
	/**
	 * test saving of account.
	 */
	@Test
	public void saveAccount() {
		Account acc = ServiceTestConfiguration.account();
		when(repo.save(acc)).thenReturn(acc);
		assertEquals(service.saveAccount(acc),acc.getId());
	}
	
	/**
	 * test saving of account with nulls.
	 */
	@Test
	public void saveAccountWithNullCounts() {
		Account acc = ServiceTestConfiguration.account();
		Account accNull = ServiceTestConfiguration.account();
		accNull.setLogincount(null);
		accNull.setLogoutcount(null);
		acc.setLogincount(0);
		acc.setLogoutcount(0);
		
		when(repo.save(acc)).thenReturn(acc);
		assertEquals(service.saveAccount(accNull),acc.getId());
	}
	
	/**
	 * test login
	 */
	@Test
	public void testLogin() {
		Account acc = ServiceTestConfiguration.account();
		when(repo.findByUseridAndPasswd(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD)).thenReturn(acc);
		when(repo.save(isA(Account.class))).thenReturn(acc);
		
		Map<String,Object> result = service.login(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD);
		assertEquals(result.get("accountid"),ServiceTestConfiguration.PROFILE_ID);
		assertNotNull(result.get("authToken"));
	}
	
	/**
	 * test login
	 */
	@Test(expected=AuthenticationException.class)
	public void testLoginNull() {
		Account acc = ServiceTestConfiguration.account();
		when(repo.findByUseridAndPasswd(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD)).thenReturn(null);
		
		service.login(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD);
	}
	/**
	 * test logout with no account found.
	 */
	@Test
	public void testLogoutNull() {
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(null);
		
		Account result = service.logout(ServiceTestConfiguration.USER_ID);
		
		assertNull(result);
	}
	/**
	 * test logout.
	 */
	@Test
	public void testLogout() {
		Account acc = ServiceTestConfiguration.account();
		
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(acc);
		
		Account result = service.logout(ServiceTestConfiguration.USER_ID);
		
		Integer i = Math.addExact(1, ServiceTestConfiguration.LOGOUT_COUNT );
		
		assertEquals(result.getLogoutcount(), i);
		
		assertNull(result.getAuthtoken());
	}
	
	/**
	 * Test Account domain object hashcode.
	 */
	@Test
	public void testAccountObject() {
		Account acc1 = ServiceTestConfiguration.account();
		Account acc2 = ServiceTestConfiguration.account();
		
		assertEquals(acc1.hashCode(),acc2.hashCode());
	}
}
