package io.pivotal.quotes.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.pivotal.quotes.configuration.TestConfiguration;
import io.pivotal.quotes.domain.CompanyInfo;
import io.pivotal.quotes.domain.Quote;
import io.pivotal.quotes.exception.SymbolNotFoundException;

import org.junit.Test;


public class QuoteServiceTest {

	QuoteService service = new QuoteService();
	
	@Test
	public void getQuote() throws Exception {
		Quote quote = service.getQuote(TestConfiguration.QUOTE_SYMBOL);
		assertEquals(TestConfiguration.QUOTE_SYMBOL, quote.getSymbol());
		assertEquals(TestConfiguration.QUOTE_NAME, quote.getName());
	}
	
	@Test(expected=SymbolNotFoundException.class)
	public void getNullQuote() throws Exception{
		Quote quote = service.getQuote(TestConfiguration.NULL_QUOTE_SYMBOL);
	}
	
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
	
	@Test
	public void getNullCompanyInfo() throws Exception {
		List<CompanyInfo> comps = service.getCompanyInfo(TestConfiguration.NULL_QUOTE_SYMBOL);
		assertTrue(comps.isEmpty());
	}
	
}
