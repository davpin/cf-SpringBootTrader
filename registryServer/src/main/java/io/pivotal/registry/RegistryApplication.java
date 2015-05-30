package io.pivotal.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {
	public static void main(String[] args) {
        //new SpringApplicationBuilder(RegistryApplication.class).web(true).run(args);
		SpringApplication.run(RegistryApplication.class, args);
    }
}
