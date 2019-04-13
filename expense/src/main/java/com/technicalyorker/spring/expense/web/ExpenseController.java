package com.technicalyorker.spring.expense.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technicalyorker.spring.expense.domain.Category;
import com.technicalyorker.spring.expense.domain.Currency;
import com.technicalyorker.spring.expense.domain.Expense;
import com.technicalyorker.spring.expense.domain.ExpenseType;
import com.technicalyorker.spring.expense.domain.User;
import com.technicalyorker.spring.expense.exception.NoCategoryFoundException;
import com.technicalyorker.spring.expense.service.CategoryService;
import com.technicalyorker.spring.expense.service.CurrencyService;
import com.technicalyorker.spring.expense.service.ExpenseService;
import com.technicalyorker.spring.expense.service.UserService;

@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
public class ExpenseController {
	@Autowired
	ExpenseService eService;
	@Autowired
	CategoryService cService;
	@Autowired
	UserService uService;
	@Autowired
	CurrencyService cuService;

	@RequestMapping("/save_expense")
	public String saveExpense(@ModelAttribute("expenseForm") Expense expense, BindingResult result,
			Principal principal) {
		expense.setUser(uService.getUser(principal.getName()));
		eService.save(expense);
		return "success";
	}

	@Autowired
	LoggedInUser loggedInUser;

	@RequestMapping("/add_expense")
	public String addExpense(Model model, Principal principal) {
		Expense expense = new Expense();
		expense.setId(-1L);
		expense.setUser(uService.getUser(principal.getName()));
		List<Currency> currencies = cuService.all();
		List<Category> cats = cService.root().getRootList();
		if (null == cats || cats.size() == 0) {
			throw new NoCategoryFoundException("No Category has been defined yet!");
		}
		Category cat = cats.get(0);
		expense.setCurrency(currencies.get(0));
		expense.setCat(cat);
		List<ExpenseType> expenseTypes = cat.getExpenseTypes();
		expense.setExpenseType(expenseTypes.get(0));
		expense.setRootCat(cService.root());
		model.addAttribute("expenseForm", expense);
		model.addAttribute("currencies", currencies);
		model.addAttribute("modes", expenseTypes);
		model.addAttribute("remaining", eService.remaining(expense));
		return "add_expense";
	}

	@RequestMapping("/load_expense_type")
	public String addExpense(@ModelAttribute("expenseForm") Expense expense, BindingResult result, Model model,
			Principal principal) {
		expense.setCat(cService.refresh(expense.getCat()));
		expense.setUser(uService.getUser(principal.getName()));
		expense.setRootCat(cService.root());
		model.addAttribute("expenseForm", expense);
		model.addAttribute("currencies", cuService.all());
		model.addAttribute("modes", expense.getCat().getExpenseTypes());
		model.addAttribute("remaining", eService.remaining(expense));
		return "add_expense";
	}

	@RequestMapping("/edit_expense")
	public String editExpense(@RequestParam(name = "id") Long id, Model model, Principal principal) {
		Expense expense = eService.findById(id);
		expense.setRootCat(cService.root());
		model.addAttribute("currencies", cuService.all());
		model.addAttribute("expenseForm", expense);
		model.addAttribute("modes", expense.getCat().getExpenseTypes());
		model.addAttribute("remaining", eService.remaining(expense));
		return "add_expense";
	}

	@RequestMapping("/delete_expense")
	public String deleteExpense(@RequestParam(name = "id") Long id, Model model, Principal principal) {
		eService.deleteById(id);
		return "redirect:/view_expenses";
	}

	@RequestMapping("/view_expenses")
	public String viewExpense(Model model, Principal principal) {
		User u = uService.getUser(principal.getName());
		model.addAttribute("expenses", eService.viewAll(u));
		return "all_expenses";
	}
}
