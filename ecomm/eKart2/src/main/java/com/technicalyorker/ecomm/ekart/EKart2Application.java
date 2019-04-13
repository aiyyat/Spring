package com.technicalyorker.ecomm.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
public class EKart2Application {

	public static void main(String[] args) {
		SpringApplication.run(EKart2Application.class, args);
	}
}
