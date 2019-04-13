package com.technicalyorker.vulpes;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.technicalyorker.vulpes.filter.XSSFilter;

@Configuration
public class XSSFilterInitializer {
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		XSSFilter compressingFilter = new XSSFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(compressingFilter);
		return registrationBean;
	}
}
