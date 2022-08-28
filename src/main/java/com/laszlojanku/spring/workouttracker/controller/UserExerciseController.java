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

import com.laszlojanku.spring.workouttracker.model.UserExercise;
import com.laszlojanku.spring.workouttracker.service.AppUserService;
import com.laszlojanku.spring.workouttracker.service.UserExerciseService;

@RestController
public class UserExerciseController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private UserExerciseService userExerciseService;
	
	@GetMapping("/user/exercises/all")
	public ResponseEntity<String> getAll(Authentication auth) {		
		List<UserExercise> userExercises = new ArrayList<UserExercise>();
		
		try {
			if (auth == null) {
				return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
			}
			int userId = appUserService.getId(auth.getName());
			userExercises = userExerciseService.getAll(userId);
		} catch (Exception e ) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(userExercises.toString(), HttpStatus.OK);	
	}
	
	@PostMapping("/user/exercises/new")
	public ResponseEntity<String> add(@RequestBody String newExerciseName, Authentication auth) {
		try {
			if (auth == null) {
				return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
			}
			int userId = appUserService.getId(auth.getName());
			userExerciseService.add(newExerciseName, userId);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Added a new exercise: " + newExerciseName, HttpStatus.OK);			
	}
	
	@DeleteMapping("/user/exercises/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int exerciseId, Authentication auth) {
		try {
			if (auth == null) {
				return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
			}
			int userId = appUserService.getId(auth.getName());
			userExerciseService.delete(exerciseId, userId);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Exercise deleted.", HttpStatus.OK);	
	}

}