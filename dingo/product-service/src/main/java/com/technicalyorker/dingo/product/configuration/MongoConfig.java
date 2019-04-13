package com.technicalyorker.dingo.product.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "products";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost");
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.technicalyorker.dingo.product.domain";
	}
}