package io.pivotal.portfolio.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

import java.math.BigDecimal;

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
	QuoteRemoteCallService quoteService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

	@Test
	public void doGetPortfolio() {
 
		when(repo.findByAccountId(ServiceTestConfiguration.ACCOUNT_ID)).thenReturn(ServiceTestConfiguration.orders());
		//when(quoteService.getUri()).thenReturn(uri);
		when(quoteService.getQuote(ServiceTestConfiguration.quote().getSymbol())).thenReturn(ServiceTestConfiguration.quote());
		//when(restTemplate.getForObject("http://" + service.quotesService +"/quote/{symbol}", Quote.class, ServiceTestConfiguration.quote().getSymbol())).thenReturn(ServiceTestConfiguration.quote());
		Portfolio folio = service.getPortfolio(ServiceTestConfiguration.ACCOUNT_ID);
	}
	@Test
	public void doSaveOrder() {
		Order returnOrder = ServiceTestConfiguration.order();
		returnOrder.setOrderId(1);
		double amount = ServiceTestConfiguration.order().getQuantity()*ServiceTestConfiguration.order().getPrice().doubleValue()+ServiceTestConfiguration.order().getOrderFee().doubleValue();
		ResponseEntity<Double> response = new ResponseEntity<Double>(100d, HttpStatus.OK);
		
		
		//when(accountService.getUri()).thenReturn(uri);
		when(restTemplate.getForEntity("http://" + service.accountsService +"/accounts/{userid}/decreaseBalance/{amount}", Double.class, ServiceTestConfiguration.order().getAccountId(), amount )).thenReturn(response);
		when(repo.save(ServiceTestConfiguration.order())).thenReturn(returnOrder);
		Order order = service.addOrder(ServiceTestConfiguration.order());
		assertEquals(order, returnOrder);
	}
	
	@Test
	public void doSaveOrderNullOrderFee() {
		Order returnOrder = ServiceTestConfiguration.order();
		returnOrder.setOrderId(1);
		double amount = returnOrder.getQuantity()*returnOrder.getPrice().doubleValue()+returnOrder.getOrderFee().doubleValue();
		ResponseEntity<Double> response = new ResponseEntity<Double>(100d, HttpStatus.OK);
		
		
		//when(accountService.getUri()).thenReturn(uri);
		when(restTemplate.getForEntity(any(), eq(Double.class), any(), any())).thenReturn(response);
		when(repo.save(isA(Order.class))).thenReturn(returnOrder);
		Order requestOrder = ServiceTestConfiguration.order();
		requestOrder.setOrderFee(null);
		Order order = service.addOrder(requestOrder);
		assertEquals(order.getOrderFee(), ServiceTestConfiguration.order().getOrderFee());
	}
	@Test
	public void doSaveOrderSellOrder() {
		Order returnOrder = ServiceTestConfiguration.sellOrder();
		returnOrder.setOrderId(1);
		double amount = ServiceTestConfiguration.sellOrder().getQuantity()*ServiceTestConfiguration.sellOrder().getPrice().doubleValue()-ServiceTestConfiguration.sellOrder().getOrderFee().doubleValue();
		ResponseEntity<Double> response = new ResponseEntity<Double>(100d, HttpStatus.OK);
		
		
		//when(accountService.getUri()).thenReturn(uri);
		when(restTemplate.getForEntity("http://" + service.accountsService +"/accounts/{userid}/increaseBalance/{amount}", Double.class, ServiceTestConfiguration.sellOrder().getAccountId(), amount )).thenReturn(response);
		when(repo.save(ServiceTestConfiguration.sellOrder())).thenReturn(returnOrder);
		Order order = service.addOrder(ServiceTestConfiguration.sellOrder());
		assertEquals(order, returnOrder);
	}

}
