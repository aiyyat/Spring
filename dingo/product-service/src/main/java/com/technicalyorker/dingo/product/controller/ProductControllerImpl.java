package com.technicalyorker.dingo.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.dingo.product.ProductReporitory;
import com.technicalyorker.dingo.product.domain.Product;

@RestController
@RequestMapping("/repo")
public class ProductControllerImpl implements ProductController {
	@Autowired
	ProductReporitory repo;

	@Override
	public List<Product> allProducts() {
		return repo.findAll();
	}

	@Override
	public void add(@RequestBody Product product) {
		System.out.println(product);
		repo.save(product);
	}
}
