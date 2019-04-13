package com.technicalyorker.dingo.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.technicalyorker.dingo.product.domain.Product;

public interface ProductController {
	@RequestMapping("/products")
	public List<Product> allProducts();

	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = { "application/json" })
	public void add(@RequestBody Product product);
}
