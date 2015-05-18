package io.pivotal.portfolio.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.pivotal.portfolio.config.ServiceTestConfiguration;
import io.pivotal.portfolio.domain.Order;
import io.pivotal.portfolio.domain.Portfolio;
import io.pivotal.portfolio.domain.Quote;
import io.pivotal.portfolio.repository.OrderRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.util.UriInfo;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

public class PortfolioServiceTest {
	MockMvc mockMvc;

	@InjectMocks
	PortfolioService service;
	
	@Mock
	OrderRepository repo;
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	UriInfo quoteService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

	@Test
	public void doGetPortfolio() {
		URI uri = null;
		try {
			uri = new URI("http://localhost");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<String, String>();
	    params.put("symbol", ServiceTestConfiguration.SYMBOL);
	    
		when(repo.findByAccountId(ServiceTestConfiguration.ACCOUNT_ID)).thenReturn(ServiceTestConfiguration.orders());
		when(quoteService.getUri()).thenReturn(uri);
		when(restTemplate.getForObject(uri.toString(), Quote.class, params)).thenReturn(ServiceTestConfiguration.quote());
		Portfolio folio = service.getPortfolio(ServiceTestConfiguration.ACCOUNT_ID);
	}
	@Test
	public void doSaveOrder() {
		Order returnOrder = ServiceTestConfiguration.order();
		returnOrder.setOrderId(1);
		when(repo.save(ServiceTestConfiguration.order())).thenReturn(returnOrder);
		Order order = service.addOrder(ServiceTestConfiguration.order());
		assertEquals(order, returnOrder);
	}

}
