package com.technicalyorker.spring.store.exception;

public class StoreException extends RuntimeException {
	private static final long serialVersionUID = -6127508569926524106L;
	private String code;

	public StoreException(String code) {
		this.code = code;
	}

	public StoreException(String e, String code) {
		super(e);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
