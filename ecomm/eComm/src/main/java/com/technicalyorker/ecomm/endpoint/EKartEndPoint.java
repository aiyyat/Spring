package com.technicalyorker.ecomm.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class EKartEndPoint {
	@Autowired
	EKartReader e;

	@FeignClient("eKart")
	interface EKartReader {
		@RequestMapping("/instance")
		public String getInstance();
	}

	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	@RequestMapping("/instance")
	public String getInstance() {
		return e.getInstance();
	}
}
