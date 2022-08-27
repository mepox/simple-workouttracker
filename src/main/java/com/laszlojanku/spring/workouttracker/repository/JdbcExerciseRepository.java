package com.laszlojanku.spring.workouttracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcExerciseRepository implements ExerciseRepository {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void add(String exerciseName, int userId) throws DataAccessException {		
		String sql = "INSERT INTO exercise (name, userId) VALUES (?, ?)";
		Object[] params = { exerciseName, userId };
		
		jdbc.update(sql, params);
	}

	@Override
	public boolean delete(String exerciseName, int userId) throws DataAccessException {
		String sql = "DELETE FROM exercise WHERE name = ? and userId = ?";
		Object[] params = { exerciseName, userId };
		
		int rowsDeleted = jdbc.update(sql, params);		
		
		return (rowsDeleted > 0) ? true : false;
	}

}