package com.technicalyorker.spring.store.exception;

public class ServiceUnavailableStoreException extends StoreException {
	private static final String SERVICE_UNAVAILABLE = "service_unavailable";
	private static final long serialVersionUID = 1345068310628444846L;

	public ServiceUnavailableStoreException() {
		super(SERVICE_UNAVAILABLE);
	}

	public ServiceUnavailableStoreException(String msg) {
		super(msg, SERVICE_UNAVAILABLE);
	}
}
