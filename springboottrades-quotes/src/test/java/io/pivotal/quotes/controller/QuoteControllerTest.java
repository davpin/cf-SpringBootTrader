package io.pivotal.quotes.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.pivotal.quotes.configuration.TestConfiguration;
import io.pivotal.quotes.service.QuoteService;
import io.pivotal.quotes.domain.CompanyInfo;
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
public class QuoteControllerTest {
	MockMvc mockMvc;

	@InjectMocks
	QuoteController controller;

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
	@Test
	public void getQuote() throws Exception {
		when(service.getQuote(TestConfiguration.QUOTE_SYMBOL)).thenReturn(
				TestConfiguration.quote());

		mockMvc.perform(
				get("/quote/" + TestConfiguration.QUOTE_SYMBOL).contentType(
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
				//.andExpect(
				//		jsonPath("$.Change",Matchers.equalTo( (Number)TestConfiguration.QUOTE_CHANGE))))
				.andExpect(
						jsonPath("$.ChangePercent").value(
								TestConfiguration.QUOTE_CHANGE_PERCENT))
				.andExpect(
						jsonPath("$.Timestamp",notNullValue()))
				.andExpect(
						jsonPath("$.MSDate").value(
								TestConfiguration.QUOTE_MSDATE));
	}
	
	/*
	 * Tests the <code>/quote</code> REST endpoint.
	 * test fetching a quote that has a null symbol and throws exception.
	 */
	@Test
	public void getNullQuote() throws Exception {
		when(service.getQuote(TestConfiguration.NULL_QUOTE_SYMBOL)).thenThrow(
				new SymbolNotFoundException(TestConfiguration.NULL_QUOTE_SYMBOL));

		mockMvc.perform(
				get("/quote/" + TestConfiguration.NULL_QUOTE_SYMBOL).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
						.andDo(print());

	}
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
				get("/company/" + TestConfiguration.QUOTE_NAME).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
						.andDo(print())
						.andExpect(jsonPath("$").isArray());
	}
}
