package io.pivotal.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * Microservice to manage user accounts.
 * 
 * Spring Boot application to provide a service to manage user accounts. 
 * The application registers with a registry service - Eureka.
 * 
 * @author David Ferreira Pinto
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
