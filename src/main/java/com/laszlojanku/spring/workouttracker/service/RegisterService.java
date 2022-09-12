package com.laszlojanku.spring.workouttracker.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.RegisterForm;
import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;
import com.laszlojanku.spring.workouttracker.repository.JdbcExerciseHistoryRepository;
import com.laszlojanku.spring.workouttracker.repository.JdbcUserExerciseRepository;

/**
 * Provides a service to handle the registering process.
 */
@Service
public class RegisterService {
	
	private JdbcAppUserRepository appUserRepository;
	private JdbcUserExerciseRepository exerciseRepository;
	private ResourceLoader resourceLoader;
	
	@Autowired
	public RegisterService(JdbcAppUserRepository appUserRepository, JdbcUserExerciseRepository exerciseRepository, 
			JdbcExerciseHistoryRepository exerciseHistoryRepository, ResourceLoader resourceLoader) {
		this.appUserRepository = appUserRepository;		
		this.exerciseRepository = exerciseRepository;
		this.resourceLoader = resourceLoader;
		
		// Add a default user
		try {
			// Adds a default user with the default exercises
			int userId = register(new RegisterForm("user", "user", "user"));
			// Adds few exercise for "today"
			exerciseHistoryRepository.add(userId, 1, 100, 12, LocalDate.now().toString());
			exerciseHistoryRepository.add(userId, 1, 100, 11, LocalDate.now().toString());
			exerciseHistoryRepository.add(userId, 1, 100, 10, LocalDate.now().toString());
			
			exerciseHistoryRepository.add(userId, 2, 60, 12, LocalDate.now().toString());
			exerciseHistoryRepository.add(userId, 2, 60, 11, LocalDate.now().toString());
			exerciseHistoryRepository.add(userId, 2, 60, 10, LocalDate.now().toString());
		} catch (Exception e) {			
			SimpleWorkoutTrackerApplication.logger.warn("Error while trying to add the default user(s): " + e.getMessage());			
		}	
	}
	
	/**
	 * Registers a new user using the RegisterForm.
	 * @param	registerForm	RegisterForm object
	 * @return					the new user's id
	 * @throws AppException
	 * @throws JdbcException
	 */
	public int register(RegisterForm registerForm) throws AppException, JdbcException  {
		// Validate username
		validateUsername(registerForm.getUsername());
		
		// Validate password
		validatePassword(registerForm.getPassword());
		
		// Check if user already exists
		if (appUserRepository.isExists(registerForm.getUsername())) {
			throw new AppException("Username already exists.");
		}
		
		// Check if password and passwordConfirm matches 
		if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
			throw new AppException("Password confirmation doesnt match.");
		}
		
		// Add the new user
		int userId = -1;
		try {			
			userId = appUserRepository.add(registerForm.getUsername(), registerForm.getPassword(), "ROLE_USER");			
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
	
	private void validateUsername(String username) throws AppException  {
		boolean result = username.matches("[a-zA-Z]+");
		
		if (!result) {
			throw new AppException("Username can only contain alphabets characters.");						
		}
	}
	
	private void validatePassword(String password) {
		// todo				
	}
	
	private void addDefaultExercisesToNewAppUser(int userId) throws IOException {
		InputStream inputStream;
		BufferedReader reader;
		
		
		inputStream = new ClassPathResource("default_exercises.txt").getInputStream();
		reader = new BufferedReader(new InputStreamReader(inputStream));
		while (reader.ready()) {
			String exercise = reader.readLine();
			exerciseRepository.add(exercise, userId);
		}
	}

}