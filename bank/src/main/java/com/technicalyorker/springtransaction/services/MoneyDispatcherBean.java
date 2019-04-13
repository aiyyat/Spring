package com.technicalyorker.springtransaction.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyDispatcherBean {
	@Transactional(propagation = Propagation.MANDATORY)
	public double prepareToDispatch(double amount) {
		if (amount > 4000) {
			throw new IllegalStateException("Cannot dispatch more than $4000");
		}
		System.out.println("ATM preparing to dispatched $" + amount);
		return amount;
	}
}
