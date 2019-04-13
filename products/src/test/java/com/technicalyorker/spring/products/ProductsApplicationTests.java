package com.technicalyorker.spring.products;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.technicalyorker.spring.products.configuration.AppConfig;
import com.technicalyorker.spring.products.domains.Product;
import com.technicalyorker.spring.products.repositories.ProductRepository;
import com.technicalyorker.spring.products.services.ProductService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfigTest.class)
@WebAppConfiguration
@ActiveProfiles("unitTest")
public class ProductsApplicationTests {
	@Autowired
	ProductService productService;
	@Autowired
	MongoTemplate mt;

	@Test
	public void contextLoads() {
	}

	@Test
	public void basic() {
		Product product = new Product();
		product.setName("heat distribution");
		product.setCompany("Alfa");
		String id = productService.createProduct(product).getId();
		Query productQuery = new Query();
		productQuery.addCriteria(Criteria.where("id").is(id));
		TestCase.assertNotNull(mt.findAndRemove(productQuery, Product.class));
	}
}

@Component
@EnableMongoRepositories
@ComponentScan(basePackageClasses = { AppConfig.class, ProductRepository.class, ProductService.class, Product.class })
class AppConfigTest {

}
