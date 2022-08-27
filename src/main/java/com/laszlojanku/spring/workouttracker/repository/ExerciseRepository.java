package com.laszlojanku.spring.workouttracker.repository;

public interface ExerciseRepository {
	
	public void add(String exerciseName, int userId);
	public boolean delete(String exerciseName, int userId);

}