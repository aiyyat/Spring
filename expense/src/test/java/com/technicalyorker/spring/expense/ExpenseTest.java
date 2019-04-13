package com.technicalyorker.spring.expense;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.technicalyorker.spring.expense.domain.Direction;
import com.technicalyorker.spring.expense.domain.Expense;
import com.technicalyorker.spring.expense.domain.ExpenseType;
import com.technicalyorker.spring.expense.domain.User;
import com.technicalyorker.spring.expense.service.ExpenseService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExpenseApplication.class)
@WebAppConfiguration
@ActiveProfiles("local-mysql")
public class ExpenseTest {
	@Autowired
	ExpenseService es;

	@Test
	public void test() {
		Expense expense = new Expense();
		User user = new User();
		user.setName("admin");
		expense.setUser(user);
		ExpenseType mode = new ExpenseType();
		mode.setDirection(Direction.PAY_BY_CARD);
		expense.setExpenseType(mode);
		//System.out.println(es.remaining(expense));
	}
}
