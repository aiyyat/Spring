package com.technicalyorker.spring.expense.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technicalyorker.spring.expense.domain.Direction;
import com.technicalyorker.spring.expense.domain.ExpenseType;
import com.technicalyorker.spring.expense.service.ExpenseTypeService;

@Controller
@Secured("ROLE_ADMIN")
public class ExpenseTypeController {
	@Autowired
	ExpenseTypeService eService;

	@RequestMapping("/add_expense_type")
	public String addExpenseType(Model model) {
		ExpenseType expenseType = new ExpenseType();
		model.addAttribute("directions", Direction.values());
		model.addAttribute("expenseTypeForm", expenseType);
		return "add_expense_type";
	}

	@RequestMapping("/save_expense_type")
	public String saveExpenseType(@ModelAttribute("expenseTypeForm") ExpenseType expenseType, BindingResult result) {
		eService.add(expenseType);
		return "success";
	}

	@RequestMapping("/edit_expense_type")
	public String editExpenseType(@RequestParam(name = "id") Long id, Model model) {
		ExpenseType expenseType = eService.findById(id);
		model.addAttribute("directions", Direction.values());
		model.addAttribute("expenseTypeForm", expenseType);
		return "add_expense_type";
	}

	@RequestMapping("/delete_expense_type")
	public String deleteExpenseType(@ModelAttribute("expenseTypeForm") ExpenseType expenseType, BindingResult result) {
		eService.delete(expenseType);
		return "success";
	}

	@RequestMapping("/all_expense_types")
	public String allExpenseTypes(Model model) {
		model.addAttribute("expenseTypes", eService.all());
		return "all_expense_types";
	}
}
