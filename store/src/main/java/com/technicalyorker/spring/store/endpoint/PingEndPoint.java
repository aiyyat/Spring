package com.technicalyorker.spring.store.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technicalyorker.spring.store.domain.Store;
import com.technicalyorker.spring.store.exception.ServiceUnavailableStoreException;
import com.technicalyorker.spring.store.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component
@Path("/ping")
@Api(value = "/ping")
public class PingEndPoint {
	@Autowired
	StoreService ss;

	@Path("/store-exception")
	@GET
	@Produces("application/json")
	@ApiOperation(value = "ServiceUnavailableStoreException")
	public List<Store> exception() {
		throw new ServiceUnavailableStoreException("Simulated Exception");
	}

	@Path("/null-ptr-exception")
	@GET
	@Produces("application/json")
	@ApiOperation(value = "NullPointerException")
	public List<Store> npe() {
		throw new NullPointerException("Nully!");
	}
}
