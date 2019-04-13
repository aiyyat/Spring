package com.technicalyorker.spring.expense.exception;

public class NoCategoryFoundException extends ExpenseApplicationException {
	private static final long serialVersionUID = 7133522071898053813L;

	public NoCategoryFoundException(String string) {
		super(string);
	}
}
