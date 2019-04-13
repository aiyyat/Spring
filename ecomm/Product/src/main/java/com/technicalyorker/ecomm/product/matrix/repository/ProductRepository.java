package com.technicalyorker.ecomm.product.matrix.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.technicalyorker.ecomm.product.matrix.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	public List<Product> findByName(String name, Pageable pageable);

	public List<Product> findByNameAndCompanyCode(String name, String companyCode);
}
