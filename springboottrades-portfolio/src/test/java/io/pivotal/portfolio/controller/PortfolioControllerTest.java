package io.pivotal.portfolio.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.pivotal.portfolio.config.ServiceTestConfiguration;
import io.pivotal.portfolio.service.PortfolioService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;

/**
 * Tests for the PortfolioController.
 * 
 * @author David Ferreira Pinto
 *
 */
public class PortfolioControllerTest {
	MockMvc mockMvc;

	@InjectMocks
	PortfolioController controller;

	@Mock
	PortfolioService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getPortfolio() throws Exception {
		when(service.getPortfolio(ServiceTestConfiguration.ACCOUNT_ID))
				.thenReturn(ServiceTestConfiguration.portfolio());

		mockMvc.perform(
				get("/portfolio/" + ServiceTestConfiguration.ACCOUNT_ID)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(
						content().contentTypeCompatibleWith(
								MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.accountId").value(
								ServiceTestConfiguration.ACCOUNT_ID))
				.andExpect(jsonPath("$.holdings.*").value(hasSize(1)))
				.andDo(print());
	}

	@Test
	public void addOrder() throws Exception {
		when(service.addOrder(ServiceTestConfiguration.order()))
		.thenReturn(ServiceTestConfiguration.order2());

mockMvc.perform(
		post("/portfolio/" + ServiceTestConfiguration.ACCOUNT_ID)
		.contentType(MediaType.APPLICATION_JSON)
				.content(
						convertObjectToJson(ServiceTestConfiguration.order())))
						.andExpect(status().isCreated()).andDo(print());

	}

	private byte[] convertObjectToJson(Object request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper.writeValueAsBytes(request);
	}

}
