package com.technicalyorker.vulpes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@SpringBootApplication
@EnableWebMvc
public class VulpesCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VulpesCustomerServiceApplication.class, args);
	}
}
