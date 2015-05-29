package io.pivotal.web.config;

import io.pivotal.web.service.MarketService;
import io.pivotal.web.service.UserService;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.cloud.util.UriInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@ServiceScan
@Profile("cloud")
public class CloudConfig extends AbstractCloudConfig {

	@Bean
	public UriInfo quoteService() {
		return connectionFactory().service("quoteService", UriInfo.class);
	}
	@Bean
	public UriInfo portfolioService() {
		return connectionFactory().service("portfolioService", UriInfo.class);
	}
	
	@Bean
	public UriInfo accountService() {
		return connectionFactory().service("accountService", UriInfo.class);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
	@Bean
	public MarketService marketService() {
		return new MarketService();
	}
	
}
