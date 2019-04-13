package com.technicalyorker.spring.expense;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.technicalyorker.spring.expense.domain.Category;
import com.technicalyorker.spring.expense.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExpenseApplication.class)
@WebAppConfiguration
@ActiveProfiles("local-mysql")
public class CategoryTest {
	@Autowired
	CategoryService cs;

	@Test
	public void test() {
		for (Category c : cs.allParents()) {
			System.out.println(c.getId());
		}
	}
}
