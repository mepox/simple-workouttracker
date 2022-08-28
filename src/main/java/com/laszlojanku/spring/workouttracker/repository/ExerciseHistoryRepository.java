package com.laszlojanku.spring.workouttracker.repository;

import java.util.List;

import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;

public interface ExerciseHistoryRepository {
	
	public void add(int userId, int exerciseId, int weight, int reps, String exercise_date);
	public List<ExerciseHistory> getAll(int userId, String exercise_date);
	public boolean delete(int id);	

}