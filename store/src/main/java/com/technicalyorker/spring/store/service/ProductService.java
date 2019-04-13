package com.technicalyorker.spring.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.store.domain.Product;
import com.technicalyorker.spring.store.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository pr;

	public List<Product> allProducts() {
		return pr.findAll();
	}

	public void createProduct(String productName, String description) {
		pr.save(new Product(productName, description));
	}

	public void deleteAll() {
		pr.deleteAll();
	}
}
