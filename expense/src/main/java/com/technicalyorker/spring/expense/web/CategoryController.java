package com.technicalyorker.spring.expense.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technicalyorker.spring.expense.domain.Category;
import com.technicalyorker.spring.expense.service.CategoryService;
import com.technicalyorker.spring.expense.service.ExpenseTypeService;

@Controller
@Secured("ROLE_ADMIN")
public class CategoryController {
	@Autowired
	CategoryService cService;
	@Autowired
	ExpenseTypeService eService;

	@RequestMapping("/add_category")
	public String addCategory(Model model) {
		model.addAttribute("parents", cService.allParents());
		Category category = new Category();
		model.addAttribute("expenseTypes", eService.all());
		model.addAttribute("categoryForm", category);
		return "add_category";
	}

	@RequestMapping("/save_category")
	public String saveCategory(@ModelAttribute("categoryForm") Category category, BindingResult result) {
		cService.add(category);
		return "success";
	}

	@RequestMapping("/edit_category")
	public String editCategory(@RequestParam(name = "id") Long id, Model model) {
		Category category = cService.findById(id);
		model.addAttribute("expenseTypes", eService.all());
		model.addAttribute("parents", cService.allParents());
		model.addAttribute("categoryForm", category);
		return "add_category";
	}

	@RequestMapping("/delete_category")
	public String deleteCategory(@ModelAttribute("categoryForm") Category category, BindingResult result) {
		cService.delete(category);
		return "success";
	}

	@RequestMapping("/all_categories")
	public String allCategories(Model model) {
		model.addAttribute("parents", cService.allParents());
		return "all_categories";
	}
}
