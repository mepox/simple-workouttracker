package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String getHomePage() {
		return "secured/home.html";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "public/login.html";
	}
	
	@GetMapping("/register")
	public String getRegisterPage() {
		return "public/register.html";
	}

}
