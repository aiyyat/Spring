package com.technicalyorker.springtransaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ATMServiceBean {
	@Autowired
	AccountBean account = null;

	@Autowired
	MoneyDispatcherBean dispatcher = null;

	private void withdrawTimes(String accountNumber, double money, int times) {
		double totalAmount = 0.00d;
		for (int i = 0; i < times; i++) {
			account.fetchBankAccount(accountNumber).withDraw(money);
			totalAmount += dispatcher.prepareToDispatch(money);
		}
		System.out.println("Dispatching $" + totalAmount);
	}

	public void withdraw(String accountNumber, double money) {
		withdrawTimes(accountNumber, money, 1);
	}

	public void withdrawTwice(String accountNumber, double money) {
		withdrawTimes(accountNumber, money, 2);
	}
}
