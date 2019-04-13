package com.technicalyorker.dingo.customer.service;

import java.util.List;

import com.technicalyorker.dingo.common.response.MessageWrapper;
import com.technicalyorker.dingo.customer.domain.Customer;

public interface CustomerService {
	MessageWrapper<List<Customer>> findPlatinumCustomers();
}
