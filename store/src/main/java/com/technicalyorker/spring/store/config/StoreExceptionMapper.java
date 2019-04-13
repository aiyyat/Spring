package com.technicalyorker.spring.store.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.technicalyorker.spring.store.exception.StoreException;
import com.technicalyorker.spring.store.utility.ErrorLoader;

@Provider
public class StoreExceptionMapper implements ExceptionMapper<StoreException> {
	@Autowired
	ErrorLoader pl;

	@Override
	public Response toResponse(StoreException exception) {
		StoreError se = pl.getStoreError(exception.getCode());
		return Response.status(se.getStatus()).entity(se).type("application/json").build();
	}
}
