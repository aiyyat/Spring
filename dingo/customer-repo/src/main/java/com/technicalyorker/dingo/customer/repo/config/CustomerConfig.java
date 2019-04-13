package com.technicalyorker.dingo.customer.repo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = { "com.technicalyorker.dingo.customer.domain" })
@ComponentScans(value = { @ComponentScan("com.technicalyorker.dingo.customer.controller") })
public class CustomerConfig {

}
