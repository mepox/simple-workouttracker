package com.laszlojanku.spring.workouttracker.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;

/**
 * Repository using JdbcTemplate to manipulate ExerciseHistory in the database.
 */
@Repository
public class JdbcExerciseHistoryRepository implements ExerciseHistoryRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * Adds a new ExerciseHistory for a user to the database.
	 * 
	 * @param userId			user's id
	 * @param userExerciseId	UserExercise's id
	 * @param weight			weight
	 * @param reps				reps
	 * @param exercise_date		date
	 * @throws 					JdbcException
	 */	
	@Override
	public void add(int userId, int userExerciseId, int weight, int reps, String exercise_date) throws DataAccessException {
		String sql = "INSERT INTO exercise_history (userId, userExerciseId, weight, reps, exercise_date) VALUES (?, ?, ?, ?, ?)";
		
		jdbc.update(sql, userId, userExerciseId, weight, reps, exercise_date);
	}
	
	/**
	 * Gets a list of all the ExerciseHistory for a user on a specific date from the database.
	 * 
	 * @param userId			user's id
	 * @param exercise_date		date
	 * @return					list of all the ExerciseHistory on the specific date
	 * @throws 					JdbcException
	 */	
	@Override
	public List<ExerciseHistory> getAll(int userId, String exercise_date) throws DataAccessException {
		List<ExerciseHistory> exerciseHistoryList = new ArrayList<ExerciseHistory>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		String sql = "SELECT * FROM exercise_history WHERE userId = ? AND exercise_date = ?";
		
		mapList = jdbc.queryForList(sql, userId, exercise_date);
		
		if (mapList != null) {
			for (Map<String, Object> map : mapList) {
				exerciseHistoryList.add(buildFromMap(map));
			}
		}
		
		return exerciseHistoryList;
	}
	
	private ExerciseHistory buildFromMap(Map<String, Object> map) {
		ExerciseHistory exerciseHistory = new ExerciseHistory();
		
		exerciseHistory.setId((int) map.get("id"));
		exerciseHistory.setUserId((int) map.get("userId"));
		exerciseHistory.setUserExerciseId((int) map.get("userExerciseId"));
		exerciseHistory.setWeight((int) map.get("weight"));
		exerciseHistory.setReps((int) map.get("reps"));
		exerciseHistory.setDate((String) map.get("exercise_date"));		
		
		return exerciseHistory;		
	}
	
	/**
	 * Deletes an ExerciseHistory using the id from the database. 
	 * 
	 * @param id	ExerciseHistory's id
	 * @throws 		AppException 
	 * @throws 		JdbcException
	 */
	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "DELETE FROM exercise_history WHERE id = ?";
		
		int rowsDeleted = jdbc.update(sql, id);
		
		return (rowsDeleted > 0) ? true : false;
	}
	
	/**
	 * Deletes an ExerciseHistory using the ExerciseHistory's id from the database. 
	 * 
	 * @param id	ExerciseHistory's id
	 * @throws 		AppException 
	 * @throws 		JdbcException
	 */
	@Override
	public boolean deleteByExerciseId(int exerciseId) throws DataAccessException {
		String sql = "DELETE FROM exercise_history WHERE userExerciseId = ?";
		
		int rowsDeleted = jdbc.update(sql, exerciseId);
		
		return (rowsDeleted > 0) ? true : false;		
	}

}