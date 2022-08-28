package com.laszlojanku.spring.workouttracker.model;

public class ExerciseHistory {	
	
	private int id;
	private int userId;
	private int userExerciseId;	
	private int weight;
	private int reps;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserExerciseId() {
		return userExerciseId;
	}
	public void setUserExerciseId(int userExerciseId) {
		this.userExerciseId = userExerciseId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\", \"userId\" : \"" + userId + "\", \"userExerciseId\" : \"" + userExerciseId + "\", \"weight\" : \""
				+ weight + "\", \"reps\" : \"" + reps + "\", \"date\" : \"" + date + "\" }";
	}

}