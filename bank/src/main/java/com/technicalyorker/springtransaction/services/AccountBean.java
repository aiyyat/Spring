package com.technicalyorker.springtransaction.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.technicalyorker.springtransaction.domainmodel.BankAccount;

@Service
public class AccountBean {
	@PersistenceContext
	EntityManager em;

	/**
	 * REQUIRES_NEW is a Transaction Attribute where a new transaction context
	 * is created every time the method is invoked.
	 * 
	 * @param account
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createBankAccount(BankAccount account) {
		em.persist(account);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public BankAccount fetchBankAccount(String accountNumber) {
		return em.find(BankAccount.class, accountNumber);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void withdraw(String accountNumber, double money) {
		fetchBankAccount(accountNumber).withDraw(money);
	}
}
