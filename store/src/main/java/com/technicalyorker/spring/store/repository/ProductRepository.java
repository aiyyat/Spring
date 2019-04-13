package com.technicalyorker.spring.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.technicalyorker.spring.store.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	public Product findByProductName(String productName);
}
