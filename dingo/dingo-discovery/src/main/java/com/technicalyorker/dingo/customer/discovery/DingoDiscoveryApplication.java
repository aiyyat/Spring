package com.technicalyorker.dingo.customer.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DingoDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DingoDiscoveryApplication.class, args);
	}
}
