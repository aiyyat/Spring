package com.technicalyorker.ecomm.product.matrix.domain;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product {
	@Id
	private String id;
	@Field("name")
	private String name;
	@Field("detailed-name")
	private String detailedName;
	@Field("company-code")
	private String companyCode;
	@Field("base-price")
	private Unit price;
	@Field("dimension")
	private ProductDimension dimension;
	private Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDetailedName() {
		return detailedName;
	}

	public void setDetailedName(String detailedName) {
		this.detailedName = detailedName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public ProductDimension getDimension() {
		return dimension;
	}

	public void setDimension(ProductDimension dimension) {
		this.dimension = dimension;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Unit getPrice() {
		return price;
	}

	public void setPrice(Unit price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", detailedName=" + detailedName + ", companyCode="
				+ companyCode + ", price=" + price + ", dimension=" + dimension + ", attributes=" + attributes + "]";
	}
}
