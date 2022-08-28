package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;

@RestController
public class AppUserController {
	
	@Autowired
	private JdbcAppUserRepository appUserRepository;
	
	@GetMapping("/user/username")
	public ResponseEntity<String> getUsername(Authentication auth) {
		if (auth == null) {
			return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(auth.getName(), HttpStatus.OK);
		}		
	}

}