package com.technicalyorker.spring.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document
public class Store {
	@Id
	private String storeId;
	private String storeName;
	private boolean display;
	private String storeAddress;

	public Store() {
	}

	public Store(String storeName, String storeAddress) {
		super();
		this.storeName = storeName;
		this.storeAddress = storeAddress;
	}

	public Store(String storeName, String storeAddress, boolean display) {
		this(storeName, storeAddress);
		this.display = display;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@ApiModelProperty(notes = "The name of the Store", required = true)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	@ApiModelProperty(notes = "The Address of the Store", required = true)
	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	@Override
	public String toString() {
		return "StoreDetail [storeId=" + storeId + ", storeName=" + storeName + ", display=" + display
				+ ", storeAddress=" + storeAddress + "]";
	}
}
