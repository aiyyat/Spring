package com.technicalyorker.vulpes.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.vulpes.domain.ReleaseBuilder;
import com.technicalyorker.vulpes.domain.ReleaseBuilder.Release;

@RestController()
public class WelcomeController {
	@RequestMapping(value = "/welcome", method = { RequestMethod.POST, RequestMethod.GET })
	public String welcome(HttpServletRequest request) {
		for (String s : request.getParameterMap().keySet()) {
			System.out.println(s + ">>>>>>>>>>" + request.getParameter(s));
		}
		return "hi";
	}

	@RequestMapping(value = "/release")
	public Release release() {
		return ReleaseBuilder.newRelease().version("0.1").date(Calendar.getInstance()).author("Achuth").build();
	}
}
