package com.technicalyorker.spring.products.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.technicalyorker.spring.products.domains.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
