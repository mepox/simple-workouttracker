package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.model.RegisterForm;
import com.laszlojanku.spring.workouttracker.service.RegisterService;

@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterForm registerForm) {
		SimpleWorkoutTrackerApplication.logger.info("Trying to register: " + registerForm.toString());
		
		try {
			registerService.register(registerForm);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Success. User registered.", HttpStatus.OK);		
	}

}