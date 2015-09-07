package io.pivotal.web;


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Trace;
import org.springframework.cloud.sleuth.instrument.scheduling.TraceSchedulingAspect;
import org.springframework.cloud.sleuth.instrument.web.TraceFilter;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class WebApplication {
	@Bean
	public Sampler<?> defaultSampler() {
		return new AlwaysSampler();
	}
	
	String skipPattern = "";
	@Autowired
	private Trace trace;
	@Bean
	public FilterRegistrationBean traceFilter(ApplicationEventPublisher publisher) {
		Pattern pattern = StringUtils.hasText(this.skipPattern) ? Pattern.compile(this.skipPattern)
				: TraceFilter.DEFAULT_SKIP_PATTERN;
		TraceFilter filter = new TraceFilter(this.trace, pattern);
		filter.setApplicationEventPublisher(publisher);
		return new FilterRegistrationBean(filter);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
