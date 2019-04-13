package com.technicalyorker.ecomm.product.matrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.technicalyorker.ecomm.product.matrix.repository.ProductRepository;
import com.technicalyorker.ecomm.product.matrix.util.ProductUtil;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan
@EnableDiscoveryClient
public class ProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Autowired
	ProductRepository repository;

	@Bean
	public CommandLineRunner loadData() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				repository.deleteAll();
				save("bottle|Pearl Pet Compression|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("cryogenic engine|Engine|Thomas Mechinary|100|cms|30|cms|30|cms|10|l|10|$");
				save("bottle|Pearl Pet Compression category 1|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 2|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 3|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 4|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 5|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 6|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 7|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 8|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 9|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 10|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 11|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 12|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
				save("bottle|Pearl Pet Compression category 13|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$");
			}
		};
	}

	private void save(String str) {
		repository.save(ProductUtil.getProduct(str));
	}
}
