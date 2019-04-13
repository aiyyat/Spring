package com.technicalyorker.springtransaction.SpringJPATransactions;

import java.util.Arrays;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.technicalyorker.springtransaction.domainmodel.BankAccount;
import com.technicalyorker.springtransaction.services.ATMServiceBean;
import com.technicalyorker.springtransaction.services.AccountBean;

public class AppTest extends TestCase {
	public void testApp() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
		ATMServiceBean atmServiceBean = (ATMServiceBean) context
				.getBean("ATMServiceBean");
		AccountBean accountBean = (AccountBean) context.getBean("accountBean");
		String accountNumber = "SA-34563";
		BankAccount account = new BankAccount();
		account.setAccountNumber(accountNumber);
		account.setAccountHolderName("Boon Lay");
		account.setBalance(3000.00);
		accountBean.createBankAccount(account);
		System.out.println(accountBean.fetchBankAccount(accountNumber));
		atmServiceBean.withdraw(accountNumber, 1000);
		atmServiceBean.withdrawTwice(accountNumber, 1001);
	}
}
