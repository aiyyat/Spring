package com.technicalyorker.dingo.customer.repo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.dingo.customer.domain.Address;
import com.technicalyorker.dingo.customer.domain.Customer;
import com.technicalyorker.dingo.customer.domain.Rating;
import com.technicalyorker.dingo.customer.repo.dao.CustomerRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.technicalyorker.dingo.customer.repo.dao" })
@EnableDiscoveryClient
public class CustomerRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRepoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(CustomerRepository cr) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				Customer customer = new Customer();
				customer.setName("Mr Nice Guy");
				customer.setPhone("9234234132");
				customer.setRating(Rating.PLATINUM);
				customer.setEmail("mrniceguy@foxtel.com.au");
				Address address = new Address();
				address.setLine1("No. 168");
				address.setLine2("Paramatta Road");
				address.setCity("Strathfield, Sydney");
				address.setCountry("Australia");
				address.setState("New South Wales");
				address.setZip("2000");
				customer.setAddress(address);
				cr.save(customer);
			}
		};
	}
}

@RestController
@RefreshScope
class Keys {
	// @Value("${apiKey}")
	private String apiKey;

	@RequestMapping("/key")
	public String apiKey() {
		return apiKey;
	}
}