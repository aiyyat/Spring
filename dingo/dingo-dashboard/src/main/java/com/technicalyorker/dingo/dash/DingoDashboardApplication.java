package com.technicalyorker.dingo.dash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class DingoDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DingoDashboardApplication.class, args);
	}
}
