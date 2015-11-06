package io.pivotal.quotes.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.pivotal.quotes.configuration.TestConfiguration;
import io.pivotal.quotes.service.QuoteService;
import io.pivotal.quotes.domain.CompanyInfo;
import io.pivotal.quotes.domain.Quote;
import io.pivotal.quotes.exception.SymbolNotFoundException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
/**
 * Tests for the QuoteController.
 * @author David Ferreira Pinto
 *
 */
public class QuoteV1ControllerTest {
	MockMvc mockMvc;

	@InjectMocks
	QuoteV1Controller controller;

	@Mock
	QuoteService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	/*
	 * Tests the <code>/quote</code> REST endpoint.
	 * test fetching a quote succesfully.
	 */
	/*
	  @Test
	 
	public void getQuote() throws Exception {
		when(service.getQuote(TestConfiguration.QUOTE_SYMBOL)).thenReturn(
				TestConfiguration.quote());

		mockMvc.perform(
				get("/v1/quote/" + TestConfiguration.QUOTE_SYMBOL).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
						.andDo(print())
				.andExpect(
						content().contentTypeCompatibleWith(
								MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.Name").value(
								TestConfiguration.QUOTE_NAME))
				.andExpect(
						jsonPath("$.Symbol").value(
								TestConfiguration.QUOTE_SYMBOL))
				.andExpect(
						jsonPath("$.LastPrice").value(
								TestConfiguration.QUOTE_LAST_PRICE))
				.andExpect(
						jsonPath("$.Change",Matchers.closeTo(TestConfiguration.QUOTE_CHANGE, new BigDecimal(0.01))))
				.andExpect(
						jsonPath("$.ChangePercent", Matchers.closeTo(TestConfiguration.QUOTE_CHANGE_PERCENT, 0.01)))
				.andExpect(
						jsonPath("$.Timestamp",notNullValue()))
				.andExpect(
						jsonPath("$.MSDate",Matchers.closeTo(
								TestConfiguration.QUOTE_MSDATE,0.01)));
	}
	*/
	
	/*
	 * Tests the <code>/quote</code> REST endpoint.
	 * test fetching a quote that has a null symbol and throws exception.
	 */
	/*
	@Test
	public void getNullQuote() throws Exception {
		when(service.getQuote(TestConfiguration.NULL_QUOTE_SYMBOL)).thenThrow(
				new SymbolNotFoundException(TestConfiguration.NULL_QUOTE_SYMBOL));

		mockMvc.perform(
				get("/v1/quote/" + TestConfiguration.NULL_QUOTE_SYMBOL).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
						.andDo(print());

	}
	*/
	/*
	 * Tests the <code>/company</code> REST endpoint.
	 * test fetching a company information.
	 */
	@Test
	public void getCompanies() throws Exception {
		List<CompanyInfo> comps = new ArrayList<>();
		comps.add(TestConfiguration.company());
		when(service.getCompanyInfo(TestConfiguration.QUOTE_NAME)).thenReturn(
				comps);
		mockMvc.perform(
				get("/v1/company/" + TestConfiguration.QUOTE_NAME).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
						.andDo(print())
						.andExpect(jsonPath("$").isArray());
	}
	/*
	 * Tests the <code>/quotes</code> REST endpoint.
	 * test fetching multiple quotes information.
	 */
	@Test
	public void getQuotes() throws Exception {
		when(service.getQuotes(TestConfiguration.QUOTE_SYMBOLS)).thenReturn(
				TestConfiguration.quotes());
		mockMvc.perform(
				get("/v1/quotes?q=" + TestConfiguration.QUOTE_SYMBOLS).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
						.andExpect(jsonPath("$",hasSize(2)))
						.andDo(print());
	}
	/*
	 * Tests the <code>/quotes</code> REST endpoint.
	 * test no query.
	 */
	@Test
	public void getQuotesEmpty() throws Exception {
		
		mockMvc.perform(
				get("/v1/quotes").contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
						.andExpect(jsonPath("$",hasSize(0)))
						.andDo(print());
	}
	/*
	 * Tests the <code>/quotes</code> REST endpoint.
	 * test no query.
	 */
	@Test
	public void getQuotesOneQuote() throws Exception {
		List<Quote> quotes = new ArrayList<>();
		quotes.add(TestConfiguration.quote());
		when(service.getQuote(TestConfiguration.QUOTE_SYMBOL)).thenReturn(
				TestConfiguration.quote());
		mockMvc.perform(
				get("/v1/quotes?q=" + TestConfiguration.QUOTE_SYMBOL).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
						.andExpect(jsonPath("$",hasSize(1)))
						.andExpect(
						content().contentTypeCompatibleWith(
								MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$[0].Name").value(
								TestConfiguration.QUOTE_NAME))
				.andExpect(
						jsonPath("$[0].Symbol").value(
								TestConfiguration.QUOTE_SYMBOL))
				.andExpect(
						jsonPath("$[0].LastPrice").value(
								TestConfiguration.QUOTE_LAST_PRICE))
				.andExpect(
						jsonPath("$[0].Change",Matchers.closeTo(TestConfiguration.QUOTE_CHANGE, new BigDecimal(0.01))))
				.andExpect(
						jsonPath("$[0].ChangePercent", Matchers.closeTo(TestConfiguration.QUOTE_CHANGE_PERCENT, 0.01)))
				.andExpect(
						jsonPath("$[0].Timestamp",notNullValue()))
				.andExpect(
						jsonPath("$[0].MSDate",Matchers.closeTo(
								TestConfiguration.QUOTE_MSDATE,0.01)))
						.andDo(print());
	}
}
