package com.technicalyorker.ecomm.product.matrix.domain;

public class ProductDimension {
	private Unit length;
	private Unit height;
	private Unit width;
	private Unit weight;
	private Unit volume;

	public Unit getLength() {
		return length;
	}

	public void setLength(Unit length) {
		this.length = length;
	}

	public Unit getHeight() {
		return height;
	}

	public void setHeight(Unit height) {
		this.height = height;
	}

	public Unit getWidth() {
		return width;
	}

	public void setWidth(Unit width) {
		this.width = width;
	}

	public Unit getWeight() {
		return weight;
	}

	public void setWeight(Unit weight) {
		this.weight = weight;
	}

	public Unit getVolume() {
		return volume;
	}

	public void setVolume(Unit volume) {
		this.volume = volume;
	}
}
