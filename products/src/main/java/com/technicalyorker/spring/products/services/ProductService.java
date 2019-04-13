package com.technicalyorker.spring.products.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.products.domains.Product;
import com.technicalyorker.spring.products.repositories.ProductRepository;

@Service("productService")
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public ProductService() {
		super();
	}

	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	public void updateProducts(List<Product> products) {
		for (Product product : products) {
			productRepository.save(product);
		}
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	public void removeProduct(Product product) {
		productRepository.delete(product);
	}

	public Product createProduct(Product product) {
		return productRepository.insert(product);
	}
}
