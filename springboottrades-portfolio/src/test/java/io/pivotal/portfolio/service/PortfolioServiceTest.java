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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Mock
	UriInfo accountService;
	
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
	    
		when(repo.findByAccountId(ServiceTestConfiguration.ACCOUNT_ID)).thenReturn(ServiceTestConfiguration.orders());
		when(quoteService.getUri()).thenReturn(uri);
		when(restTemplate.getForObject(uri.toString()+"/quote/{symbol}", Quote.class, ServiceTestConfiguration.quote().getSymbol())).thenReturn(ServiceTestConfiguration.quote());
		Portfolio folio = service.getPortfolio(ServiceTestConfiguration.ACCOUNT_ID);
	}
	@Test
	public void doSaveOrder() {
		URI uri = null;
		try {
			uri = new URI("http://localhost");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Order returnOrder = ServiceTestConfiguration.order();
		returnOrder.setOrderId(1);
		double amount = ServiceTestConfiguration.order().getQuantity()*ServiceTestConfiguration.order().getPrice().doubleValue()+ServiceTestConfiguration.order().getOrderFee().doubleValue();
		ResponseEntity<Double> response = new ResponseEntity<Double>(100d, HttpStatus.OK);
		
		
		when(accountService.getUri()).thenReturn(uri);
		when(restTemplate.getForEntity(uri.toString()+"/accounts/{userid}/decreaseBalance/{amount}", Double.class, ServiceTestConfiguration.order().getAccountId(), amount )).thenReturn(response);
		when(repo.save(ServiceTestConfiguration.order())).thenReturn(returnOrder);
		Order order = service.addOrder(ServiceTestConfiguration.order());
		assertEquals(order, returnOrder);
	}

}
