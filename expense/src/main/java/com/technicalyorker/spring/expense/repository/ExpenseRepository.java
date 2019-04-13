package com.technicalyorker.spring.expense.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.technicalyorker.spring.expense.domain.Expense;

@Transactional
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("select e from Expense e where e.user.id=?1 or 'ROLE_ADMIN'=?2 order by e.date desc")
	List<Expense> viewAll(Long id, String role);

	@Query("select e.expenseType.direction,sum(e.amount) from Expense e where e.user.id=?1 and e.currency.id=?2 group by e.expenseType.direction")
	ArrayList<Object[]> getIncomeGroupsByUserAndCurrency(Long userId, Integer currencyId);
}
