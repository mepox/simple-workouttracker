package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.model.RegisterForm;
import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;

@Service
public class RegisterService {
	
	@Autowired
	private JdbcAppUserRepository repository;
	
	public void register(RegisterForm registerForm) throws Exception  {
		// Validate username
		validateUsername(registerForm.getUsername());
		
		// Validate password
		validatePassword(registerForm.getPassword());
		
		// Check if user already exists
		if (repository.isExists(registerForm.getUsername())) {
			throw new Exception("Username already exists.");
		}
		
		// Check if password and passwordConfirm matches 
		if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
			throw new Exception("Password confirmation doesnt match.");
		}
		
		// Add the new user				
		try {			
			repository.add(registerForm.getUsername(), registerForm.getPassword(), "ROLE_USER");			
		} catch (DataAccessException e) {
			throw new Exception("Database error.");
		}
	}
	
	private void validateUsername(String username) throws Exception  {
		if (username.contains("!")) {
			throw new Exception("Username cannot contain special characters.");						
		}
	}
	
	private void validatePassword(String password) {
				
	}

}