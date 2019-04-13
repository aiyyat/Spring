package com.technicalyorker.spring.expense;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.technicalyorker.spring.expense.repository",
		"com.technicalyorker.spring.expense.service" })
@EnableJpaRepositories
public class AppConfig {

}
