package com.laszlojanku.spring.workouttracker.repository;

public interface ExerciseHistoryRepository {
	
	public void add(int userId, int workoutId, int weight, int reps, String date);	

}