package com.technicalyorker.ecomm.product.matrix.domain;

public class Unit {
	private String magnitude;
	private String dimension;

	public Unit(String magnitude, String dimension) {
		this.dimension = dimension;
		this.magnitude = magnitude;
	}

	public String getMagnitude() {
		return magnitude;
	}

	public String getDimension() {
		return dimension;
	}
}
