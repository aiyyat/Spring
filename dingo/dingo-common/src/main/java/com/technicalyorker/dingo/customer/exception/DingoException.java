package com.technicalyorker.dingo.customer.exception;

public class DingoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DingoException(Throwable e) {
		super(e);
	}
}
