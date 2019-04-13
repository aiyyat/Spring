package com.technicalyorker.spring.store.endpoint;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technicalyorker.spring.store.domain.Store;
import com.technicalyorker.spring.store.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Component
@Path("/")
@Api(value = "/api")
public class StoreEndPoint {
	@Autowired
	StoreService ss;

	@Path("/all")
	@GET
	@Produces("application/json")
	@ApiOperation(value = "List all Stores", notes = "LIST ALL STORES", response = Store.class, responseContainer = "List")
	public List<Store> getAllStores() {
		return ss.allStores();
	}

	@Path("/create/{storeName}/{storeAddress}")
	@POST
	@Produces("application/json")
	@ApiOperation(value = "create a new Store", notes = "USE THIS TO CREATE A NEW STORE")
	public void createStore(
			@ApiParam(value = "Page to fetch", required = true) @PathParam("storeName") String storeName,
			@ApiParam(value = "Pages to fetch", required = true) @PathParam("storeAddress") String storeAddress) {
		ss.createStore(storeName, storeAddress);
	}

	@Path("/deleteAll")
	@DELETE
	@ApiOperation(value = "deleteAll", nickname = "DELETE ALL")
	public void deleteAll() {
		ss.deleteAll();
	}

	@Path("/delete/{storeName}")
	@DELETE
	@ApiOperation(value = "delete a Store By StoreName", nickname = "DELETE A STORE BY STORENAME")
	public void deleteStoreByStoreName(@PathParam("storeName") String storeName) {
		ss.deleteStoreByName(storeName);
	}
}
