package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the client's REST calls that are related to the AppUser.
 */
@RestController
public class AppUserController {
	
	/**
	 * Handles the client request to retrieve the current user's name.
	 * @param	auth	Authentication token received from the client
	 * @return			user's name or an error message		
	 */	
	@GetMapping("/user/username")
	public ResponseEntity<String> getUsername(Authentication auth) {
		if (auth == null) {
			return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(auth.getName(), HttpStatus.OK);
		}		
	}

}