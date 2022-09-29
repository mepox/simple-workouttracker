package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.RegisterForm;
import com.laszlojanku.spring.workouttracker.service.RegisterService;

/**
 * Handles the client's REST API requests that are related to registering.
 */
@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	/**
	 * Handles the client's POST request to register.
	 * 
	 * @param	registerForm	RegisterForm object sent by the client
	 * @return					status message and HttpStatus
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterForm registerForm) {
		SimpleWorkoutTrackerApplication.logger.info("Trying to register: " + registerForm.toString());
		
		try {
			registerService.register(registerForm);
		} catch (AppException | JdbcException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Success. User registered.", HttpStatus.OK);		
	}

}