package com.technicalyorker.ecomm.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.technicalyorker.ecomm.model.Summary;

@RestController
public class SummaryEndpoint {
	@Autowired
	SummaryReader e;
	@Value("${ecomm.version}")
	String version;

	@FeignClient("product-matrix")
	interface SummaryReader {
		@RequestMapping(method = RequestMethod.GET, value = "/products/count")
		public Long getProductCount();
	}

	@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
	@RequestMapping("/summary")
	public Summary getSummary() {
		Summary s = new Summary();
		s.setDbRecs(e.getProductCount());
		s.setVersion(version);
		return s;
	}
}
