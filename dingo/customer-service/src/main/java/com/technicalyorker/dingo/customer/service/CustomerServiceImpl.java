package com.technicalyorker.dingo.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.dingo.common.response.MessageWrapper;
import com.technicalyorker.dingo.customer.client.CustomerRepoClient;
import com.technicalyorker.dingo.customer.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepoClient client;

	@Override
	public MessageWrapper<List<Customer>> findPlatinumCustomers() {
		return client.findPlatinumCustomers();
	}
}
