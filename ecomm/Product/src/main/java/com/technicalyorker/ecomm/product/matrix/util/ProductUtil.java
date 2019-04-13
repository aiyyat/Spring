package com.technicalyorker.ecomm.product.matrix.util;

import com.technicalyorker.ecomm.product.matrix.domain.Product;
import com.technicalyorker.ecomm.product.matrix.domain.ProductDimension;
import com.technicalyorker.ecomm.product.matrix.domain.Unit;

public class ProductUtil {
	public static final Product getProduct(String productLine) {
		try {
			String[] split = productLine.split("\\|");
			Product product = new Product();
			product.setName(split[0]);
			product.setDetailedName(split[1]);
			product.setCompanyCode(split[2]);
			ProductDimension dimension = new ProductDimension();
			dimension.setHeight(new Unit(split[3], split[4]));
			dimension.setWidth(new Unit(split[5], split[6]));
			dimension.setWeight(new Unit(split[7], split[8]));
			dimension.setVolume(new Unit(split[9], split[10]));
			product.setDimension(dimension);
			product.setPrice(new Unit(split[11], split[12]));
			return product;
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal Input: " + productLine + "\n Use Seperator | ");
		}
	}

	public static void main(String[] args) {
		System.out.println(getProduct("bottle|Pearl Pet Compression|Crown Plastics|10|cms|3|cms|3|cms|1|l|10|$"));
	}
}
