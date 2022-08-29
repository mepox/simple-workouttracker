package com.laszlojanku.spring.workouttracker.model;

/**
 * Represent the UserExercise.
 */
public class UserExercise {
	
	private int id;
	private String name;
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Returns the object's fields in JSON String.
	 * @return JSON String
	 */
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\", \"name\" : \"" + name + "\", \"userId\" : \"" + userId + "\"}";
	}
	
}