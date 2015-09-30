package io.pivotal.quotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * Microservice to fetch current quotes.
 * 
 * Spring Boot application to provide a service to retrieve current Quote information. 
 * The application registers with a registry service - Eureka.
 * 
 * @author David Ferreira Pinto
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class QuotesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
}

