package com.laszlojanku.spring.workouttracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;
import com.laszlojanku.spring.workouttracker.repository.JdbcExerciseHistoryRepository;

@Service
public class ExerciseHistoryService {
	
	@Autowired
	private JdbcExerciseHistoryRepository exerciseHistoryRepository;
	
	public void add(int userId, int exersieId, int weight, int reps, String exercise_date) throws Exception {
		try {
			exerciseHistoryRepository.add(userId, exersieId, weight, reps, exercise_date);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't add new exercise to the history.");
		}
	}
	
	public List<ExerciseHistory> getAll(int userId, String exercise_date) throws Exception {
		List<ExerciseHistory> exerciseHistoryList = new ArrayList<ExerciseHistory>();
		
		try {
			exerciseHistoryList = exerciseHistoryRepository.getAll(userId, exercise_date);
		} catch (DataAccessException e) {
			throw new Exception("Database error. Couldn't get the exercises from the history.");
		}
		
		return exerciseHistoryList;
	}
	
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