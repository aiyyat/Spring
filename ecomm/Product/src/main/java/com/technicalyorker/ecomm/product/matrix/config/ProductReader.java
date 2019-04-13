package com.technicalyorker.ecomm.product.matrix.config;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;

import com.technicalyorker.ecomm.product.matrix.domain.Product;
import com.technicalyorker.ecomm.product.matrix.util.ProductUtil;

@Provider
public class ProductReader implements MessageBodyReader<Product> {

	@Override
	public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.equals(Product.class);
	}

	@Override
	public Product readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		try {
			return ProductUtil.getProduct(IOUtils.toString(entityStream, "UTF-8"));
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
}
