package com.laszlojanku.spring.workouttracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;
import com.laszlojanku.spring.workouttracker.repository.JdbcExerciseHistoryRepository;

/**
 * Provides a service to manipulate ExerciseHistory in the database.
 */
@Service
public class ExerciseHistoryService {
	
	@Autowired
	private JdbcExerciseHistoryRepository exerciseHistoryRepository;
	
	/**
	 * Adds a new ExerciseHistory for a user to the database.
	 * @param userId			user's id
	 * @param userExerciseId	UserExercise's id
	 * @param weight			weight
	 * @param reps				reps
	 * @param exercise_date		date
	 * @throws JdbcException
	 */	
	public void add(int userId, int userExerciseId, int weight, int reps, String exercise_date) throws JdbcException {
		
		try {
			exerciseHistoryRepository.add(userId, userExerciseId, weight, reps, exercise_date);
		} catch (DataAccessException e) {
			throw new JdbcException("Database error. Couldn't add new exercise to the history.");
		}
	}
	
	/**
	 * Adds a new ExerciseHistory for a user to the database
	 * @param exerciseHistory	ExerciseHistory object
	 * @throws JdbcException
	 */
	public void add(ExerciseHistory exerciseHistory) throws JdbcException {
		int userId = exerciseHistory.getUserId();
		int userExerciseId = exerciseHistory.getUserExerciseId();
		int weight = exerciseHistory.getWeight();
		int reps = exerciseHistory.getReps();
		String exercise_date = exerciseHistory.getDate();
		
		add(userId, userExerciseId, weight, reps, exercise_date);
	}
	
	/**
	 * Gets a list of all the ExerciseHistory for a user on a specific date from the database.
	 * @param userId			user's id
	 * @param exercise_date		date
	 * @return					list of all the ExerciseHistory on the specific date
	 * @throws JdbcException
	 */	
	public List<ExerciseHistory> getAll(int userId, String exercise_date) throws JdbcException {
		List<ExerciseHistory> exerciseHistoryList = new ArrayList<ExerciseHistory>();
		
		try {
			exerciseHistoryList = exerciseHistoryRepository.getAll(userId, exercise_date);
		} catch (DataAccessException e) {
			throw new JdbcException("Database error. Couldn't get the exercises from the history.");
		}
		
		return exerciseHistoryList;
	}
	
	/**
	 * Deletes an ExerciseHistory using the id from the database. 
	 * @param id	ExerciseHistory's id
	 * @throws Exception 
	 * @throws JdbcException
	 */
	public void delete(int id) throws Exception, JdbcException {
		boolean isDeleted = false;
		
		try {
			isDeleted = exerciseHistoryRepository.delete(id);
		} catch (DataAccessException e) {
			throw new JdbcException("Database error. Couldn't delete the exercise from the history.");
		}
		
		if (!isDeleted) {
			throw new Exception("Exercise not found in the history.");
		}
	}

}