package com.laszlojanku.spring.workouttracker.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.model.UserExercise;

/**
 * Repository using JdbcTemplate to manipulate UserExercise in the database.
 */
@Repository
public class JdbcUserExerciseRepository implements UserExerciseRepository {

	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * Adds a new UserExercise to the database.
	 * 
	 * @param	exerciseName	new exercise's name
	 * @param	userId			user's id who owns this exercise
	 * @throws					DataAccessException on database error
	 */
	@Override
	public void add(String exerciseName, int userId) throws DataAccessException {		
		String sql = "INSERT INTO user_exercise (name, userId) VALUES (?, ?)";
		
		jdbc.update(sql, exerciseName, userId);
	}
	
	/**
	 * Gets all the UserExercises that belongs to a specific user from the database.
	 * 
	 * @param	userId	user's id
	 * @return			list of all UserExercises that the user has
	 * @throws			DataAccessException on database error
	 */	
	@Override
	public List<UserExercise> getAll(int userId) throws DataAccessException {
		List<UserExercise> userExercises = new ArrayList<UserExercise>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		String sql = "SELECT * FROM user_exercise WHERE userId = ?";
		
		mapList = jdbc.queryForList(sql, userId);
		
		if (mapList != null) {
			for (Map<String, Object> map : mapList) {
				userExercises.add(buildFromMap(map));
			}
		}
		
		return userExercises;
	}
	
	private UserExercise buildFromMap(Map<String, Object> map) {
		UserExercise userExercise = new UserExercise();
		
		userExercise.setId((int) map.get("id"));
		userExercise.setName((String) map.get("name"));
		userExercise.setUserId((int) map.get("userId"));
		
		return userExercise;		
	}
	
	/**
	 * Checks if an exercise exists for a user in the database.
	 * 
	 * @param	exerciseName	exercise's name
	 * @param	userId			user's id
	 * @return					true if exists
	 * @throws					DataAccessException on database error
	 */
	@Override
	public boolean isExists(String exerciseName, int userId) throws DataAccessException {
		String sql = "SELECT count(*) FROM user_exercise WHERE name = ? AND userId = ?";
		
		int count = jdbc.queryForObject(sql, Integer.class, exerciseName, userId);
		
		return (count > 0);
	}
	
	/**
	 * Deletes a UserExercise by id from the database.
	 * 
	 * @param	id	UserExercise's id
	 * @return		true if successful
	 * @throws		DataAccessException on database error
	 */
	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "DELETE FROM user_exercise WHERE id = ?";
		
		int rowsDeleted = jdbc.update(sql, id);		
		
		return (rowsDeleted > 0);
	}
	
	/**
	 * Gets a UserExercise that belongs to a specific user from the database.
	 * 
	 * @param	exerciseName	exercise's name
	 * @param	userId			user's id
	 * @return					UserExercise
	 * @throws					DataAccessException on database error
	 */
	@Override
	public UserExercise get(String exerciseName, int userId) throws DataAccessException {
		String sql = "SELECT * FROM user_exercise WHERE name = ? AND userId = ?";
		
		Map<String, Object> map = jdbc.queryForMap(sql, exerciseName, userId);
		
		return buildFromMap(map);
	}
	
	/**
	 * Gets a UserExercise that belongs to a specific user from the database.
	 * 
	 * @param	exerciseId		exercise's id
	 * @param	userId			user's id
	 * @return					UserExercise
	 * @throws					DataAccessException on database error
	 */
	@Override
	public UserExercise get(int exerciseId, int userId) throws DataAccessException {
		String sql = "SELECT * FROM user_exercise WHERE id = ? AND userId = ?";
		
		Map<String, Object> map = jdbc.queryForMap(sql, exerciseId, userId);
		
		return buildFromMap(map);
	}

}