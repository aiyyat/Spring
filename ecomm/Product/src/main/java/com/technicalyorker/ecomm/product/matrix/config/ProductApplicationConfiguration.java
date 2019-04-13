package com.technicalyorker.ecomm.product.matrix.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.technicalyorker.ecomm.product.matrix.endpoint.ProductEndpoint;

@Configuration
public class ProductApplicationConfiguration extends ResourceConfig {
	public ProductApplicationConfiguration() {
		register(ProductEndpoint.class);
		register(ProductReader.class);
	}
}
