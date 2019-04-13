package com.technicalyorker.spring.expense.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.technicalyorker.spring.expense.service.UserService;

@Controller
public class WelcomeController {
	@Autowired
	UserService uService;
	@Autowired
	private LoggedInUser loggedInUser;

	@RequestMapping({ "/", "/welcome" })
	public String welcome(Principal principal) {
		if (principal != null) {
			loggedInUser.setUser(uService.getUser(principal.getName()));
		}
		return "welcome";
	}

	@RequestMapping("/aboutus")
	public void aboutus() {
	}
}
