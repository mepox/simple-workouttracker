package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.model.RegisterForm;
import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;
import com.laszlojanku.spring.workouttracker.repository.JdbcExerciseRepository;

@Service
public class RegisterService {	
	
	private JdbcAppUserRepository appUserRepository;
	private JdbcExerciseRepository exerciseRepository;
	
	@Autowired
	public RegisterService(JdbcAppUserRepository appUserRepository, JdbcExerciseRepository exerciseRepository) {
		this.appUserRepository = appUserRepository;		
		this.exerciseRepository = exerciseRepository;
		
		// Add a default user
		try {
			register(new RegisterForm("user", "user", "user"));
		} catch (Exception e) {			
			SimpleWorkoutTrackerApplication.logger.warn("Error while trying to add the default user(s): " + e.getMessage());			
		}		
	}
	
	// Add default user and workouts

	// Add default workouts 
	
	public int register(RegisterForm registerForm) throws Exception  {
		// Validate username
		validateUsername(registerForm.getUsername());
		
		// Validate password
		validatePassword(registerForm.getPassword());
		
		// Check if user already exists
		if (appUserRepository.isExists(registerForm.getUsername())) {
			throw new Exception("Username already exists.");
		}
		
		// Check if password and passwordConfirm matches 
		if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
			throw new Exception("Password confirmation doesnt match.");
		}
		
		// Add the new user
		int userId = -1;
		try {			
			userId = appUserRepository.add(registerForm.getUsername(), registerForm.getPassword(), "ROLE_USER");			
		} catch (DataAccessException e) {
			throw new Exception("Database error.");
		}
		
		// Add default exercises to the new user
		addDefaultExercisesToNewAppUser(userId);
		
		return userId;
	}
	
	private void validateUsername(String username) throws Exception  {
		if (username.contains("!")) {
			throw new Exception("Username cannot contain special characters.");						
		}
	}
	
	private void validatePassword(String password) {
				
	}
	
	private void addDefaultExercisesToNewAppUser(int userId) {		
		exerciseRepository.add("Flat Barbell Bench Press", userId);
		exerciseRepository.add("Incline Barbell Bench Press", userId);
	}

}