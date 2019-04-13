package com.technicalyorker.spring.expense.util;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseUtil {

	public static boolean atleastOneElement(List<?> list) {
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	public static BigDecimal getCurrencyValue(BigDecimal bd) {
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
