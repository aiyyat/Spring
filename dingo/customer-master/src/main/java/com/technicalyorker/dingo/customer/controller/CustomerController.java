package com.technicalyorker.dingo.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.technicalyorker.dingo.common.response.MessageWrapper;
import com.technicalyorker.dingo.customer.domain.Customer;

public interface CustomerController {
	@RequestMapping(value = "/platinums", method = RequestMethod.GET)
	public MessageWrapper<List<Customer>> findPlatinumCustomers();
}
