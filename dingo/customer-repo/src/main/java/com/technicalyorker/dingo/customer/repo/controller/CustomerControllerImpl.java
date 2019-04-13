package com.technicalyorker.dingo.customer.repo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.dingo.common.response.MessageWrapper;
import com.technicalyorker.dingo.customer.controller.CustomerController;
import com.technicalyorker.dingo.customer.domain.Customer;
import com.technicalyorker.dingo.customer.domain.Rating;
import com.technicalyorker.dingo.customer.repo.dao.CustomerRepository;

@RestController
@RequestMapping("/data")
public class CustomerControllerImpl implements CustomerController {
	@Autowired
	CustomerRepository repository;

	@Override
	public MessageWrapper<List<Customer>> findPlatinumCustomers() {
		List<Customer> customers = repository.findByRating(Rating.PLATINUM);
		return new MessageWrapper<List<Customer>>(customers);
	}
}
