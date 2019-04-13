package com.technicalyorker.spring.expense.web;

import com.technicalyorker.spring.expense.domain.User;

public interface LoggedInUser {
	public void setUser(User user);

	public User getUser();
}
