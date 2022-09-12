package com.laszlojanku.spring.workouttracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.UserExercise;
import com.laszlojanku.spring.workouttracker.service.AppUserService;
import com.laszlojanku.spring.workouttracker.service.UserExerciseService;

/**
 * Handles the client's REST API requests that are related to the User's exercises.
 * 
 * UserExercise can be added and deleted. Also all UserExercise can be retrieved.
 */
@RestController
public class UserExerciseController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private UserExerciseService userExerciseService;
	
	/**
	 * Handles the client GET request to retrieve all the UserExercises.
	 * @param	auth	Authentication token sent by the client
	 * @return			List of all UserExercise in JSON String and HttpStatus
	 */
	@GetMapping("/user/exercises/all")
	public ResponseEntity<String> getAll(Authentication auth) {	
		if (auth == null) {
			return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
		}
		
		List<UserExercise> userExercises = new ArrayList<UserExercise>();
		
		try {
			int userId = appUserService.getId(auth.getName());
			userExercises = userExerciseService.getAll(userId);
		} catch (AppException | JdbcException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(userExercises.toString(), HttpStatus.OK);	
	}
	
	/**
	 * Handles the client POST request to add a new exercise.
	 * @param	newExerciseName	the new exercise's name
	 * @param	auth			Authentication token sent by the client
	 * @return					status message and HttpStatus
	 */
	@PostMapping("/user/exercises/new")
	public ResponseEntity<String> add(@RequestBody String newExerciseName, Authentication auth) {
		if (auth == null) {
			return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			int userId = appUserService.getId(auth.getName());
			userExerciseService.add(newExerciseName, userId);
		} catch (AppException | JdbcException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Added a new exercise: " + newExerciseName, HttpStatus.OK);			
	}
	
	/**
	 * Handles the client DELETE request the delete a UserExercise
	 * @param	id	the id of the UserExercise
	 * @return		status message and HttpStatus
	 */
	@DeleteMapping("/user/exercises/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		try {
			userExerciseService.delete(id);
		} catch (AppException | JdbcException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Exercise deleted.", HttpStatus.OK);	
	}

}