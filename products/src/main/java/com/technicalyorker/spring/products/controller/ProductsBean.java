package com.technicalyorker.spring.products.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technicalyorker.spring.products.domains.Product;
import com.technicalyorker.spring.products.services.ProductService;

@Component("productsBean")
@ManagedBean
public class ProductsBean {
	@Autowired
	private ProductService productService;
	private List<Product> products = new ArrayList<>();

	@PostConstruct
	public void fetchProducts() {
		this.products = productService.allProducts();
	}

	public String updateProducts() {
		try {
			productService.updateProducts(products);
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}

	public void onRowEdit(RowEditEvent event) {
		productService.updateProduct((Product) event.getObject());
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("Do nothing!");
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
