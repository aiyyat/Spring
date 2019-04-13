package com.technicalyorker.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import com.technicalyorker.employee.domain.Employee;
import com.technicalyorker.employee.repository.EmployeeRepository;

@Service
@RepositoryRestResource
public class EmployeeService {
	@Autowired
	EmployeeRepository repository;

	@Cacheable(value = "employeecache", key = "#id")
	public Employee getEmployee(Long id) {
		System.out.println("Loading...");
		return repository.findOne(id);
	}
}
