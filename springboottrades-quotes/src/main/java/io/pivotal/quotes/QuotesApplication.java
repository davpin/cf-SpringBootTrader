package io.pivotal.quotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Microservice to fetch current quotes.
 * 
 * Spring Boot application to provide a service to retrieve current Quote information.
 * 
 * @author David Ferreira Pinto
 *
 */
@SpringBootApplication
public class QuotesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
}

