package com.technicalyorker.spring.store.exception;

public class MovedPermenantlyStoreException extends StoreException {
	private static final String MOVED_PERMANENTLY = "moved_permanently";
	private static final long serialVersionUID = 1345068310628444846L;

	public MovedPermenantlyStoreException() {
		super(MOVED_PERMANENTLY);
	}

	public MovedPermenantlyStoreException(String e) {
		super(e, MOVED_PERMANENTLY);
	}
}
