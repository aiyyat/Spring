package com.technicalyorker.spring.expense.exception;

public class ExpenseApplicationException extends RuntimeException {
	private static final long serialVersionUID = -6697028149918158397L;
	public ExpenseApplicationException(String string) {
		super(string);
	}
}
