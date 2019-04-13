package com.technicalyorker.employee.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.employee.domain.Employee;
import com.technicalyorker.employee.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeEndPoint {
	@Autowired
	EmployeeService es;

	@RequestMapping("/call")
	public Employee call() {
		return es.getEmployee(1l);
	}
}
