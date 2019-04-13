package com.technicalyorker.dingo.customer.repo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicalyorker.dingo.customer.domain.Customer;
import com.technicalyorker.dingo.customer.domain.Rating;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByRating(Rating rating);
}
