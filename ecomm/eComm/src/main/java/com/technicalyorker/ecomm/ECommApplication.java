package com.technicalyorker.ecomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@ComponentScan
@EnableWebMvc
@EnableHystrix
public class ECommApplication {
	public static void main(String[] args) {
		SpringApplication.run(ECommApplication.class, args);
	}
}
