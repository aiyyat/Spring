package com.technicalyorker.spring.store.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.context.annotation.Configuration;

import com.technicalyorker.spring.store.endpoint.PingEndPoint;
import com.technicalyorker.spring.store.endpoint.StoreEndPoint;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		registerEndpoints();
		configureSwagger();
	}

	private void configureSwagger() {
		register(ApiListingResource.class);
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/api");
		beanConfig.setResourcePackage("com.technicalyorker.spring");
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
	}

	private void registerEndpoints() {
		register(WadlResource.class);
		register(StoreEndPoint.class);
		register(PingEndPoint.class);
		register(ApiListingResource.class);
		register(SwaggerSerializers.class);
		register(StoreExceptionMapper.class);
	}
}
