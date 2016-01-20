package io.pivotal.portfolio.service;

import io.pivotal.portfolio.PortfolioApplication;
import io.pivotal.portfolio.config.ServiceTestConfiguration;
import io.pivotal.portfolio.domain.Quote;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PortfolioApplication.class)
public class QuoteRemoteCallServiceTest {

	@Value("${pivotal.quotesService.name}")
	private String quotesURI;
	
	@Autowired
	@InjectMocks
	QuoteRemoteCallService service;
	
	@Mock
	RestTemplate restTemplate;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * resttemplate not being injected into service thus cannot test success of hystrix
	 */
	@Test
	@Ignore
	public void doGetQuote() {
		when(restTemplate.getForObject("http://" + quotesURI + "/quotes?q={symbol}", Quote[].class, ServiceTestConfiguration.SYMBOL)).
				thenReturn( new Quote[]{ServiceTestConfiguration.quote()});

		List<Quote> quote = service.getQuotes(ServiceTestConfiguration.SYMBOL);
		assertEquals(ServiceTestConfiguration.quote(),quote.get(0));
	}
	@Test
	public void doGetQuoteFailure() {
		when(restTemplate.getForObject("http://" + quotesURI + "/quotes?q={symbol}", Quote[].class, ServiceTestConfiguration.SYMBOL)).
				thenThrow(new RuntimeException("Deliberately throwing an exception 1"));

		List<Quote> quote = service.getQuotes(ServiceTestConfiguration.SYMBOL);
		assertNotEquals(ServiceTestConfiguration.quote(),quote.get(0));
		Quote emptyQuote = new Quote();
		emptyQuote.setSymbol(ServiceTestConfiguration.SYMBOL);
		emptyQuote.setStatus("FAILED");
		assertEquals(Collections.singletonList(emptyQuote),quote);
	}
}
