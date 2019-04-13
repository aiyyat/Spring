 	package com.technicalyorker.dingo.customer.microproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class DingoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DingoApiGatewayApplication.class, args);
	}
}
