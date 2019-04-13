package com.technicalyorker.dingo.customer.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.technicalyorker.dingo.common.response.MessageWrapper;
import com.technicalyorker.dingo.common.response.Status;
import com.technicalyorker.dingo.customer.domain.Customer;
import com.technicalyorker.dingo.customer.service.CustomerService;

@RestController
@RequestMapping("/service")
public class CustomerControllerImpl implements CustomerController {
	@Autowired
	CustomerService customerService;

	@Override
	@HystrixCommand(fallbackMethod = "listFallBack")
	public MessageWrapper<List<Customer>> findPlatinumCustomers() {
		return customerService.findPlatinumCustomers();
	}

	public MessageWrapper<List<Customer>> listFallBack() {
		return new MessageWrapper<>(Status.FAILURE, "Service Unavailable, Please try after sometime!",
				Collections.EMPTY_LIST);
	}
}
