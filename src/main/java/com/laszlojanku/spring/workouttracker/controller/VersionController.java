package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the client's REST API requests to retreive the current version of the app.
 */
@RestController
public class VersionController {
	
	@Value("${app.version}")
	private String appVersion;
	
	/**
	 * Handles the client's GET request to retrieve the current version of the app.
	 *
	 * @return	the app version number and a HttpStatus
	 */
	@GetMapping("/version")
	public ResponseEntity<String> getVersion() {
		return new ResponseEntity<String>(appVersion, HttpStatus.OK);
	}

}