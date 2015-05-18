package io.pivotal.accounts.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
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

	@Test
	public void doFindAccount() {
		when(repo.findOne(ServiceTestConfiguration.PROFILE_ID)).thenReturn(ServiceTestConfiguration.account());
		assertEquals(service.findAccount(ServiceTestConfiguration.PROFILE_ID).toString(),ServiceTestConfiguration.account().toString());
	}
	
	@Test(expected=NoRecordsFoundException.class)
	public void doFindNullAccount() {
		when(repo.findOne(999)).thenReturn(null);
		service.findAccount(999);
	}
	
	@Test
	public void doFindAccountByAuthToken() {
		when(repo.findByAuthtoken(ServiceTestConfiguration.AUTH_TOKEN)).thenReturn(ServiceTestConfiguration.account());
		
		assertEquals(service.findAccountprofileByAuthtoken(ServiceTestConfiguration.AUTH_TOKEN).toString(),ServiceTestConfiguration.account().toString());
	}
	
	@Test(expected=AuthenticationException.class)
	public void doFindNullAccountByAuthToken() {
		when(repo.findByAuthtoken("faef8649-280d-4ba4-bdf6-574e758a04a8")).thenReturn(null);
		
		service.findAccountprofileByAuthtoken("faef8649-280d-4ba4-bdf6-574e758a04a8");
	}
	
	@Test
	public void saveAccount() {
		Account acc = ServiceTestConfiguration.account();
		when(repo.save(acc)).thenReturn(acc);
		assertEquals(service.saveAccount(acc),acc.getId());
	}
}
