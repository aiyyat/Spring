package com.technicalyorker.spring.store.exception;

public class InternalServerStoreException extends StoreException {
	private static final String INTERNAL_SERVER_ERROR = "internal_server_error";
	private static final long serialVersionUID = 1345068310628444846L;

	public InternalServerStoreException() {
		super(INTERNAL_SERVER_ERROR);
	}

	public InternalServerStoreException(String e) {
		super(e, INTERNAL_SERVER_ERROR);
	}
}
