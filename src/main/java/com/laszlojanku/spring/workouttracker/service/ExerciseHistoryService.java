package com.laszlojanku.spring.workouttracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
	 * @throws 					Exception if something went wrong
	 */	
	public void add(int userId, int userExerciseId, int weight, int reps, String exercise_date) throws Exception {
		
		try {
			exerciseHistoryRepository.add(userId, userExerciseId, weight, reps, exercise_date);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't add new exercise to the history.");
		}
	}
	
	/**
	 * Adds a new ExerciseHistory for a user to the database
	 * @param exerciseHistory	ExerciseHistory object
	 * @throws 					Exception if something went wrong
	 */
	public void add(ExerciseHistory exerciseHistory) throws Exception {
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
	 * @throws 					Exception if something went wrong
	 */	
	public List<ExerciseHistory> getAll(int userId, String exercise_date) throws Exception {
		List<ExerciseHistory> exerciseHistoryList = new ArrayList<ExerciseHistory>();
		
		try {
			exerciseHistoryList = exerciseHistoryRepository.getAll(userId, exercise_date);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't get the exercises from the history.");
		}
		
		return exerciseHistoryList;
	}
	
	/**
	 * Deletes an ExerciseHistory using the id from the database. 
	 * @param id	ExerciseHistory's id
	 * @throws		Exception if something went wrong 
	 */
	public void delete(int id) throws Exception {
		boolean isDeleted = false;
		
		try {
			isDeleted = exerciseHistoryRepository.delete(id);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't delete the exercise from the history.");
		}
		
		if (!isDeleted) {
			throw new Exception("Exercise not found in the history.");
		}
	}

}