package io.pivotal.quotes.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.pivotal.quotes.configuration.TestConfiguration;
import io.pivotal.quotes.domain.CompanyInfo;
import io.pivotal.quotes.domain.Quote;
import io.pivotal.quotes.exception.SymbolNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Tests the QuoteService.
 * @author David Ferreira Pinto
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource(properties = { "pivotal.quotes.quotes_url:http://dev.markitondemand.com/Api/v2/Quote/json?symbol={symbol}","pivotal.quotes.companies_url:http://dev.markitondemand.com/Api/v2/Lookup/json?input={name}" })
//@PropertySource("classpath:application.yml")
//@ContextConfiguration(loader = SpringApplicationContextLoader.class)
//@SpringApplicationConfiguration(classes = TestContextConfiguration.class)
//@WebAppConfiguration
public class QuoteServiceTest {
	MockMvc mockMvc;
	
	@InjectMocks
	QuoteService service;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
		
	    //TODO: fix this hack! properties should be obtained from external config.
	    
		service.quote_url = "http://dev.markitondemand.com/Api/v2/Quote/json?symbol={symbol}";
		service.company_url = "http://dev.markitondemand.com/Api/v2/Lookup/json?input={name}";
	    
	}
	/**
	 * Tests retrieving a quote from the external service.
	 * @throws Exception
	 */
	@Test
	public void getQuote() throws Exception {
		Quote quote = service.getQuote(TestConfiguration.QUOTE_SYMBOL);
		assertEquals(TestConfiguration.QUOTE_SYMBOL, quote.getSymbol());
		assertEquals(TestConfiguration.QUOTE_NAME, quote.getName());
	}
	/**
	 * Tests retrieving a quote with an unknown/null symbol from the external service.
	 * @throws Exception
	 */
	@Test(expected=SymbolNotFoundException.class)
	public void getNullQuote() throws Exception{
		Quote quote = service.getQuote(TestConfiguration.NULL_QUOTE_SYMBOL);
	}
	
	/**
	 * tests retrieving company information from external service.
	 * @throws Exception
	 */
	@Test
	public void getCompanyInfo() throws Exception {
		List<CompanyInfo> comps = service.getCompanyInfo(TestConfiguration.QUOTE_SYMBOL);
		assertFalse(comps.isEmpty());
		boolean pass = false;
		for (CompanyInfo info : comps) {
			if (info.getSymbol().equals(TestConfiguration.QUOTE_SYMBOL)) {
				pass = true;
			}
		}
		assertTrue(pass);
	}
	/**
	 * tests retrieving company information from external service with unkown company.
	 * @throws Exception
	 */
	@Test
	public void getNullCompanyInfo() throws Exception {
		List<CompanyInfo> comps = service.getCompanyInfo(TestConfiguration.NULL_QUOTE_SYMBOL);
		assertTrue(comps.isEmpty());
	}	
}

