package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.model.LoginForm;
import com.laszlojanku.spring.workouttracker.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login/perform_login")
	public ResponseEntity<String> loginUser(@RequestBody LoginForm loginForm) {
		SimpleWorkoutTrackerApplication.logger.info("Trying to login with: " + loginForm.toString());
		
		try {
			loginService.login(loginForm);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Login successful.", HttpStatus.OK);		
	}	

}