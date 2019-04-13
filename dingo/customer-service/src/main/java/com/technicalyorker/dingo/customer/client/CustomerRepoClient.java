package com.technicalyorker.dingo.customer.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.technicalyorker.dingo.customer.controller.CustomerController;

/**
 * Second way of reading REST
 */
@FeignClient(value = "customer-repo", path = "/data")
public interface CustomerRepoClient extends CustomerController {

}