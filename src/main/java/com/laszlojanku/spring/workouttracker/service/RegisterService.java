package com.laszlojanku.spring.workouttracker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.RegisterForm;
import com.laszlojanku.spring.workouttracker.validator.PasswordValidator;
import com.laszlojanku.spring.workouttracker.validator.UsernameValidator;
import com.laszlojanku.spring.workouttracker.validator.ValidatorResponse;

/**
 * Provides a service to handle the registering process.
 */
@Service
public class RegisterService {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private UserExerciseService userExerciseService;
	
	@Autowired
	private UsernameValidator usernameValidator;
	
	@Autowired
	private PasswordValidator passwordValidator;
	
	/**
	 * Registers a new user using the RegisterForm.
	 * @param	registerForm	RegisterForm object
	 * @return					the new user's id
	 * @throws AppException
	 * @throws JdbcException
	 */
	public int register(RegisterForm registerForm) throws AppException, JdbcException  {
		// Validate username
		ValidatorResponse validatorResponse = usernameValidator.validate(registerForm.getUsername());
		
		if (!validatorResponse.isValid()) {
			throw new AppException(validatorResponse.getMessage());						
		}
		
		// Validate password
		validatorResponse = passwordValidator.validate(registerForm.getPassword());
		
		if (!validatorResponse.isValid()) {
			throw new AppException(validatorResponse.getMessage());
		}
		
		
		// Check if user already exists
		if (appUserService.isExists(registerForm.getUsername())) {
			throw new AppException("Username already exists.");
		}
		
		// Check if password and passwordConfirm matches 
		if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
			throw new AppException("Password confirmation doesnt match.");
		}
		
		// Add the new user
		int userId = -1;
		try {			
			userId = appUserService.add(registerForm.getUsername(), registerForm.getPassword(), "ROLE_USER");			
		} catch (DataAccessException e) {
			throw new JdbcException("Database error.");
		}
		
		// Add default exercises to the new user
		try {
			addDefaultExercisesToNewAppUser(userId);
		} catch (IOException e) {
			throw new AppException("Error while trying to add the default exercises for the new user.");
		}		
		
		return userId;
	}
	
	private void addDefaultExercisesToNewAppUser(int userId) throws IOException, JdbcException, AppException {
		InputStream inputStream;
		BufferedReader reader;		
		
		inputStream = new ClassPathResource("default_exercises.txt").getInputStream();
		reader = new BufferedReader(new InputStreamReader(inputStream));
		while (reader.ready()) {
			String exercise = reader.readLine();
			userExerciseService.add(exercise, userId);
		}
	}

}