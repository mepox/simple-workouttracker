package com.laszlojanku.spring.workouttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.model.UserExercise;
import com.laszlojanku.spring.workouttracker.repository.JdbcUserExerciseRepository;

@Service
public class UserExerciseService {
	
	@Autowired
	private JdbcUserExerciseRepository userExerciseRepository;
	
	public void add(String newExerciseName, int userId) throws Exception {
		// Validate new exercise name, it can be only text
		if (!newExerciseName.matches("^[ A-Za-z]+$")) {
			throw new Exception("Exercise name can only contain Alphabets and Space.");
		}
		
		// Check if already exists
		if (userExerciseRepository.isExists(newExerciseName, userId)) {
			throw new Exception("This exercise already exists.");
		}		
		
		try {
			userExerciseRepository.add(newExerciseName, userId);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't add new exercise.");
		}
	}
	
	public List<UserExercise> getAll(int userId) throws Exception {
		List<UserExercise> userExercises = null;
		
		try {
			userExercises = userExerciseRepository.getAll(userId);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't get the user's exercises.");
		}
		
		return userExercises;
	}
	
	public UserExercise get(String exerciseName, int userId) throws Exception {
		UserExercise userExercise;
		
		try {
			userExercise = userExerciseRepository.get(exerciseName, userId);
		} catch (DataAccessException e) {
			throw new Exception("Exercise not found.");
		}
		
		return userExercise;
	}
	
	public UserExercise get(int exerciseId, int userId) throws Exception {
		UserExercise userExercise;
		
		try {
			userExercise = userExerciseRepository.get(exerciseId, userId);
		} catch (DataAccessException e) {
			throw new Exception("Exercise not found.");
		}
		
		return userExercise;
	}
	
	public void delete(String exerciseName, int userId) throws Exception {
		boolean isDeleted = false;
		try {
			isDeleted = userExerciseRepository.delete(exerciseName, userId);
		} catch (DataAccessException e) {
			throw new Exception("Database error.");
		}
		
		if (!isDeleted) {
			throw new Exception("Exercise not found.");
		}
	}
	
	public void delete(int exerciseId, int userId) throws Exception {
		delete(get(exerciseId, userId).getName(), userId);
	}

}