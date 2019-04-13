package com.technicalyorker.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.technicalyorker.employee.domain.Employee;
import com.technicalyorker.employee.repository.EmployeeRepository;

@SpringBootApplication
@EnableWebMvc
@EnableCaching
public class Region1Application {

	public static void main(String[] args) {
		SpringApplication.run(Region1Application.class, args);
	}

	@Autowired
	EmployeeRepository er;

	@Bean
	public CommandLineRunner init() {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				Employee manager = new Employee();
				manager.setName("Manager");
				//Employee supervisor = new Employee();
				//supervisor.setName("Supervisor");
				//supervisor.setManager(manager);
				//manager.getSubordinates().add(supervisor);
				//er.save(supervisor);
				er.save(manager);
			}
		};
	}
}
