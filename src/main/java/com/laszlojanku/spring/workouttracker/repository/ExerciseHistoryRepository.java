package com.laszlojanku.spring.workouttracker.repository;

import java.util.List;

import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;

public interface ExerciseHistoryRepository {
	
	public void add(int userId, int exerciseId, int weight, int reps, String exerciseDate);
	public List<ExerciseHistory> getAll(int userId, String exerciseDate);	
	public boolean delete(int id);
	public boolean deleteByExerciseId(int exerciseId);

}