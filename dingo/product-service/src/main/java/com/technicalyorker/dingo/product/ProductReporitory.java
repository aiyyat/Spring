package com.technicalyorker.dingo.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.technicalyorker.dingo.product.domain.Product;

public interface ProductReporitory extends MongoRepository<Product, String> {

}
