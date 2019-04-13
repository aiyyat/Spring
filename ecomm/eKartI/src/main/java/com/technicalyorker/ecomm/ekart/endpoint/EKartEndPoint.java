package com.technicalyorker.ecomm.ekart.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class EKartEndPoint {
	@Value("${ekart.instance}")
	private String instance;

	@RequestMapping("/instance")
	public String getInstance() {
		return instance;
	}
}
