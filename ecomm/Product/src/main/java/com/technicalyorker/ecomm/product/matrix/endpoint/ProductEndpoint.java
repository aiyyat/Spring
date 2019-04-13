package com.technicalyorker.ecomm.product.matrix.endpoint;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.technicalyorker.ecomm.product.matrix.constant.GlobalConstants;
import com.technicalyorker.ecomm.product.matrix.domain.Product;
import com.technicalyorker.ecomm.product.matrix.repository.ProductRepository;

@Service
@Path("/products/")
public class ProductEndpoint {
	@Autowired
	ProductRepository repository;

	@Context
	UriInfo uriInfo;

	@Path("/count")
	@GET
	@Produces("application/json")
	public Long getProductCount() {
		return repository.count();
	}

	@Path("/{name}")
	@GET
	@Produces("application/json")
	public Response getProductsByName(@PathParam("name") String name) {
		System.out.println(name);
		return getProductsByName(name, 0, 5);
	}

	@GET
	@Path("/{name}/{at}/{count}")
	@Produces("application/json")
	public Response getProductsByName(@PathParam("name") String name, @PathParam("at") Integer at,
			@PathParam("count") Integer count) {
		System.out.println(name + ":" + at + ":" + count);
		List<Product> products = repository.findByName(name, new PageRequest(at, count));
		URI self = uriInfo.getBaseUriBuilder().path(GlobalConstants.PRODUCT_PATH).path(name).path(at.toString())
				.path(count.toString()).build();
		URI next = uriInfo.getBaseUriBuilder().path(GlobalConstants.PRODUCT_PATH).path(name)
				.path(Integer.toString(at + count).toString()).path(count.toString()).build();
		return Response.ok(Entity.json(products).getEntity()).link("self", self.toString())
				.link("next", next.toString()).build();
	}

	@Path("/create")
	@POST
	public Response createProduct(Product product) {
		repository.save(product);
		return Response.ok().build();
	}
}
