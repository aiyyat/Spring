package com.technicalyorker.spring.expense.web;

import static com.technicalyorker.spring.expense.constants.Constants.ROLE_ADMIN;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technicalyorker.spring.expense.domain.Currency;
import com.technicalyorker.spring.expense.domain.Direction;
import com.technicalyorker.spring.expense.domain.User;
import com.technicalyorker.spring.expense.service.CurrencyService;
import com.technicalyorker.spring.expense.service.ExpenseService;
import com.technicalyorker.spring.expense.service.UserService;
import com.technicalyorker.spring.expense.util.ExpenseUtil;

@Controller
@Secured("ROLE_ADMIN")
public class UserController {
	@Autowired
	UserService uService;
	@Autowired
	ExpenseService eService;
	@Autowired
	CurrencyService cService;

	@RequestMapping("/add_user")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("status", null);
		return "add_user";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/all_users")
	public String allUsers(Model model, Principal me) {
		model.addAttribute("users", uService.all(me.getName()));
		return "all_users";
	}

	@RequestMapping("/edit_user")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public String editUser(@RequestParam Long id, Model model, Principal me) {
		User user = uService.getUser(me.getName());
		if (user.getRole().equals(ROLE_ADMIN) || user.getId() == id) {
			model.addAttribute("user", uService.getUserById(id));
			String status = "";
			for (Currency c : cService.all()) {
				Map<Direction, BigDecimal> item = eService.allAmounts(id, c.getId());
				status += "Total Earnings(" + c.getCode() + "):" + ""
						+ ExpenseUtil
								.getCurrencyValue(
										item.get(Direction.ACCOUNT_CREDIT).add(item.get(Direction.CASH_INCOME)))
								.toPlainString();
				status += "<br/>Total Expenditure(" + c.getCode() + "):" + ""
						+ (item.get(Direction.PAY_BY_CARD).add(item.get(Direction.PAY_BY_CASH)));
				status += "<br/>Cash in Account(" + c.getCode() + "):" + ""
						+ ExpenseUtil.getCurrencyValue(item.get(Direction.ACCOUNT_CREDIT)
								.subtract(item.get(Direction.WITHDRAW_CASH)).subtract(item.get(Direction.PAY_BY_CARD)))
								.toPlainString();
				status += "<br/>Cash in Hand(" + c.getCode() + "):" + ""
						+ (ExpenseUtil.getCurrencyValue(item.get(Direction.WITHDRAW_CASH)
								.add(item.get(Direction.CASH_INCOME)).subtract(item.get(Direction.PAY_BY_CASH)))
								+ "<br/><br/>");
			}
			model.addAttribute("status", status);
			return "add_user";
		}
		return "failure";
	}

	@RequestMapping("/delete_user")
	public String deleteUsers(@RequestParam Long id, Principal me) {
		User user = uService.getUser(me.getName());
		if (user.getRole().equals(ROLE_ADMIN)) {
			uService.deleteById(id, me.getName());
			return "success";
		}
		return "failure";
	}

	@RequestMapping("/save_user")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public String saveUser(User user, BindingResult result, Model model, Principal me) {
		User u = uService.getUser(me.getName());
		if (!user.getRole().equals(u.getRole()) && !u.getRole().equals(ROLE_ADMIN)) {
			model.addAttribute("reason", "Cannot Change role for Non Admin Users");
			return "failure";
		}
		uService.addUser(user);
		return "success";
	}
}
