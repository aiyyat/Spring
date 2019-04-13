package com.technicalyorker.spring.expense.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.technicalyorker.spring.expense.service.CategoryService;
import com.technicalyorker.spring.expense.service.UserService;

@Component
public class ApplicationLoader implements CommandLineRunner {
	@Autowired
	CategoryService cService;
	@Autowired
	UserService uService;

	@Override
	public void run(String... strings) throws Exception {
	}
}