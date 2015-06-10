package io.pivotal.web.service;


import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

/**
 * Tests the QuoteService.
 * @author David Ferreira Pinto
 *
 */
public class MarketServiceTest {
	MockMvc mockMvc;

	@InjectMocks
	MarketService service;
	
	@Mock
	RestTemplate restTemplate;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

}
