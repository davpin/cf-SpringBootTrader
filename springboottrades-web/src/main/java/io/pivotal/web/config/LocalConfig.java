package io.pivotal.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.util.UriInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("local")
public class LocalConfig {
	@Value("${quote.url:localhost}")
	private String quoteHost;
	@Value("${quote.port:8083}")
	private String quotePort;
	
	@Value("${account.url:localhost}")
	private String accountHost;
	@Value("${account.port:8082}")
	private String accountPort;
	
	@Value("${portfolio.url:localhost}")
	private String portfolioHost;
	@Value("${portfolio.port:8081}")
	private String portfolioPort;
	
	@Bean
	public UriInfo quoteService() {
		UriInfo info = new UriInfo("http",quoteHost,Integer.parseInt(quotePort),null, null, null, null, null);
		return info;
	}
	
	@Bean
	public UriInfo accountService() {
		return new UriInfo("http",accountHost,Integer.parseInt(accountPort),null, null,null, null, null);
	}
	@Bean
	public UriInfo portfolioService() {
		return new UriInfo("http",portfolioHost,Integer.parseInt(portfolioPort),null, null, null, null, null);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
