package com.technicalyorker.dingo.customer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.technicalyorker.dingo.common.response.MessageWrapper;
import com.technicalyorker.dingo.customer.client.CustomerRepoClient;
import com.technicalyorker.dingo.customer.domain.Customer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(DiscoveryClient client) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				try {
					client.getInstances("customer-repo")
							.forEach(r -> System.out.println("Customer Repo:" + r.getHost() + ":" + r.getPort()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	/**
	 * One way of reading REST
	 */
	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}

	/**
	 * Two different ways of invoking the Data Service End-Point
	 * 
	 * @param template
	 * @param client
	 * @return
	 */
	@Bean
	public CommandLineRunner read(RestTemplate template, CustomerRepoClient client) {
		return (String... arg0) -> {
			try {
				ParameterizedTypeReference<MessageWrapper<List<Customer>>> d = new ParameterizedTypeReference<MessageWrapper<List<Customer>>>() {
				};
				System.out.printf("\n\t\t 1:%s", client.findPlatinumCustomers().getPayload());
				System.out.printf("\n\t\t 2:%s",
						template.exchange("http://customer-repo/data/platinums", HttpMethod.GET, null, d).getBody()
								.getPayload());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
}
