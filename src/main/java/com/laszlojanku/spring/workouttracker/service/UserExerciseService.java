package com.laszlojanku.spring.workouttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.UserExercise;
import com.laszlojanku.spring.workouttracker.repository.JdbcExerciseHistoryRepository;
import com.laszlojanku.spring.workouttracker.repository.JdbcUserExerciseRepository;

/**
 * Provides a service to manipulate UserExercise in the database.
 */
@Service
public class UserExerciseService {
	
	@Autowired
	private JdbcUserExerciseRepository userExerciseRepository;
	
	@Autowired
	private JdbcExerciseHistoryRepository exerciseHistoryRepository;
	
	/**
	 * Adds a new UserExercise to a user to the database.
	 * @param newExerciseName	new exercise's name
	 * @param userId			user's id
	 * @throws Exception
	 * @throws JdbcException
	 */
	public void add(String newExerciseName, int userId) throws Exception, JdbcException {
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
			throw new JdbcException("Database error. Couldn't add new exercise.");
		}
	}
	
	/**
	 * Gets all the UserExercise for a user from the database.
	 * @param userId	user's id
	 * @return			list of all the UserExercise for the user
	 * @throws JdbcException
	 */
	public List<UserExercise> getAll(int userId) throws JdbcException {
		List<UserExercise> userExercises = null;
		
		try {
			userExercises = userExerciseRepository.getAll(userId);
		} catch (DataAccessException e) {
			throw new JdbcException("Database error. Couldn't get the user's exercises.");
		}
		
		return userExercises;
	}
	
	/**
	 * Deletes a UserExercise by id from the database.
	 * @param id	UserExercise's id
	 * @throws JdbcException
	 */
	public void delete(int id) throws JdbcException, Exception {
		// First we need to delete the exercise from history (foreign key)
		try {
			exerciseHistoryRepository.deleteByExerciseId(id);
		} catch (DataAccessException e) {
			throw new JdbcException("Database error.");
		}
		
		boolean isDeleted = false;
		try {
			isDeleted = userExerciseRepository.delete(id);
		} catch (DataAccessException e) {
			throw new JdbcException("Database error.");
		}
		
		if (!isDeleted) {
			throw new Exception("Exercise not found.");
		}
	}

}