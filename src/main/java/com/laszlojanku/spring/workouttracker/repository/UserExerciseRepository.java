package com.laszlojanku.spring.workouttracker.repository;

import java.util.List;

import com.laszlojanku.spring.workouttracker.model.UserExercise;

public interface UserExerciseRepository {
	
	public void add(String exerciseName, int userId);
	public List<UserExercise> getAll(int userId);
	public UserExercise get(String exerciseName, int userId);
	public UserExercise get(int id, int userId);
	public boolean isExists(String exerciseName, int userId);
	public boolean delete(int id);

}