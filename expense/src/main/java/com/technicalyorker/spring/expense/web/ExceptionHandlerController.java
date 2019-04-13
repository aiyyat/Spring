package com.technicalyorker.spring.expense.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public String defaultErrorHandler(HttpServletRequest request, Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("reason", e.getMessage());
		return "failure";
	}
}