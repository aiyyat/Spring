package com.technicalyorker.spring.expense.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.expense.domain.Direction;
import com.technicalyorker.spring.expense.domain.Expense;
import com.technicalyorker.spring.expense.domain.User;
import com.technicalyorker.spring.expense.repository.ExpenseRepository;
import com.technicalyorker.spring.expense.util.ExpenseUtil;

@Service
@Repository
public class ExpenseService {
	private static final BigDecimal MINUS_ONE = new BigDecimal(-1.0);
	@Autowired
	ExpenseRepository eRepo;

	public void save(Expense e) {
		System.out.println(e.getDate());
		eRepo.save(e);
	}

	public List<Expense> viewAll(User user) {
		return eRepo.viewAll(user.getId(), user.getRole());
	}

	public Expense findById(Long id) {
		return eRepo.getOne(id);
	}

	public void deleteById(Long id) {
		Expense e = new Expense();
		e.setId(id);
		eRepo.delete(e);
	}

	public BigDecimal remaining(Expense expense) {
		if (null == expense.getExpenseType()) {
			return MINUS_ONE;
		}
		Map<Direction, BigDecimal> amts = allAmounts(expense.getUser().getId(), expense.getCurrency().getId());
		if (expense.getExpenseType().getDirection() == Direction.PAY_BY_CARD
				|| expense.getExpenseType().getDirection() == Direction.WITHDRAW_CASH) {
			return ExpenseUtil.getCurrencyValue(amts.get(Direction.ACCOUNT_CREDIT)
					.subtract(amts.get(Direction.WITHDRAW_CASH)).subtract(amts.get(Direction.PAY_BY_CARD)));
		} else if (expense.getExpenseType().getDirection() == Direction.PAY_BY_CASH) {
			return ExpenseUtil.getCurrencyValue(amts.get(Direction.WITHDRAW_CASH).add(amts.get(Direction.CASH_INCOME))
					.subtract(amts.get(Direction.PAY_BY_CASH)));
		}
		return MINUS_ONE;
	}

	public Map<Direction, BigDecimal> allAmounts(Long userId, Integer currencyId) {
		Map<Direction, BigDecimal> map = new TreeMap<>();
		List<Object[]> amounts = (List<Object[]>) eRepo.getIncomeGroupsByUserAndCurrency(userId, currencyId);
		for (Object[] amount : amounts) {
			map.put(Direction.valueOf(amount[0].toString()),
					ExpenseUtil.getCurrencyValue(new BigDecimal(amount[1].toString())));
		}
		for (Direction direction : Direction.values()) {
			if (null == map.get(direction)) {
				map.put(direction, BigDecimal.ZERO);
			}
		}
		return map;
	}
}
