package com.technicalyorker.spring.expense.web;

import com.technicalyorker.spring.expense.domain.User;

public class LoggedInUserImpl implements LoggedInUser {
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
