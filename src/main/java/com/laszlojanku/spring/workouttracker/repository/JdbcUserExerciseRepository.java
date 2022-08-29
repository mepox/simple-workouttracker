package com.laszlojanku.spring.workouttracker.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.model.UserExercise;

@Repository
public class JdbcUserExerciseRepository implements UserExerciseRepository {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void add(String exerciseName, int userId) throws DataAccessException {		
		String sql = "INSERT INTO user_exercise (name, userId) VALUES (?, ?)";
		Object[] params = { exerciseName, userId };
		
		jdbc.update(sql, params);
	}
	
	@Override
	public List<UserExercise> getAll(int userId) throws DataAccessException {
		List<UserExercise> userExercises = new ArrayList<UserExercise>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		String sql = "SELECT * FROM user_exercise WHERE userId = ?";
		Object[] params = { userId };
		
		mapList = jdbc.queryForList(sql, params);
		
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

	@Override
	public boolean isExists(String exerciseName, int userId) throws DataAccessException {
		String sql = "SELECT count(*) FROM user_exercise WHERE name = ? AND userId = ?";
		Object[] params = { exerciseName, userId };
		
		int count = jdbc.queryForObject(sql, Integer.class, params);
		
		return (count > 0) ? true : false;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		String sql = "DELETE FROM user_exercise WHERE id = ?";
		Object[] params = { id };
		
		int rowsDeleted = jdbc.update(sql, params);		
		
		return (rowsDeleted > 0) ? true : false;
	}

	@Override
	public UserExercise get(String exerciseName, int userId) throws DataAccessException {
		String sql = "SELECT * FROM user_exercise WHERE name = ? AND userId = ?";
		Object[] params = { exerciseName, userId };
		
		Map<String, Object> map = jdbc.queryForMap(sql, params);
		
		return buildFromMap(map);
	}

	@Override
	public UserExercise get(int exerciseId, int userId) throws DataAccessException {
		String sql = "SELECT * FROM user_exercise WHERE id = ? AND userId = ?";
		Object[] params = { exerciseId, userId };
		
		Map<String, Object> map = jdbc.queryForMap(sql, params);
		
		return buildFromMap(map);
	}

}