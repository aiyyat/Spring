package com.technicalyorker.spring.expense.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.technicalyorker.spring.expense.domain.Currency;
import com.technicalyorker.spring.expense.service.CurrencyService;

@Controller
@Secured("ROLE_ADMIN")
public class CurrencyController {
	@Autowired
	CurrencyService cService;

	@RequestMapping("/add_currency")
	public String addCurrency(Model model) {
		Currency currency = new Currency();
		model.addAttribute("currencyForm", currency);
		return "add_currency";
	}

	@RequestMapping("/save_currency")
	public String saveCurrency(@ModelAttribute("currencyForm") Currency currency, BindingResult result) {
		cService.add(currency);
		return "success";
	}

	@RequestMapping("/edit_currency/{code}")
	public String editCurrency(@PathVariable("code") String code, Model model) {
		Currency currency = cService.findByCode(code);
		model.addAttribute("currencyForm", currency);
		return "add_currency";
	}

	@RequestMapping("/delete_currency/{code}")
	public String deleteCurrency(@PathVariable("code") String code) {
		Currency currency = cService.findByCode(code);
		cService.delete(currency);
		return "success";
	}

	@RequestMapping("/all_currencies")
	public String allCategories(Model model) {
		model.addAttribute("currencies", cService.all());
		return "all_currencies";
	}
}
