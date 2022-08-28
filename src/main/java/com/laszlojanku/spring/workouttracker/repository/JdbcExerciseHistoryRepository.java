package com.laszlojanku.spring.workouttracker.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;

@Repository
public class JdbcExerciseHistoryRepository implements ExerciseHistoryRepository {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void add(int userId, int userExerciseId, int weight, int reps, String exercise_date) throws DataAccessException {
		String sql = "INSERT INTO exercise_history (userId, userExerciseId, weight, reps, exercise_date) VALUES (?, ?, ?, ?, ?)";
		Object[] params = { userId, userExerciseId, weight, reps, exercise_date };
		
		jdbc.update(sql, params);
	}

	@Override
	public List<ExerciseHistory> getAll(int userId, String exercise_date) throws DataAccessException {
		List<ExerciseHistory> exerciseHistoryList = new ArrayList<ExerciseHistory>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		String sql = "SELECT * FROM exercise_history WHERE userId = ? AND exercise_date = ?";
		Object[] params = { userId, exercise_date };
		
		mapList = jdbc.queryForList(sql, params);
		
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

	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "DELETE FROM exercise_history WHERE id = ?";
		Object[] params = { id };
		
		int rowsDeleted = jdbc.update(sql, params);
		
		return (rowsDeleted > 0) ? true : false;
	}

}