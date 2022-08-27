package com.laszlojanku.spring.workouttracker.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.model.AppUser;

@Repository
public class JdbcAppUserRepository implements AppUserRepository {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void add(String username, String password, String rolename) throws DataAccessException {
		String sql = "INSERT INTO appuser (username, password, rolename) VALUES (?, ?, ?)";
		Object[] params = { username, password, rolename };
		
		jdbc.update(sql, params);		
	}

	@Override
	public AppUser get(String username) throws DataAccessException {
		// Return null if the user doesn't exists
		if (!isExists(username)) {
			return null;
		}
		
		String sql = "SELECT * FROM appuser WHERE username = ?";
		Object[] params = { username };
		
		Map<String, Object> row = jdbc.queryForMap(sql, params);
		
		AppUser appUser = buildAppUserByRow(row);

		return appUser;
	}
	
	private AppUser buildAppUserByRow(Map<String, Object> row) {	
		int id = (int)row.get("id");
		String username = (String)row.get("username");
		String password = (String)row.get("password");
		String rolename = (String)row.get("rolename");		
		
		return new AppUser(id, username, password, rolename);
	}

	@Override
	public boolean isExists(String username) throws DataAccessException {
		String sql = "SELECT count(*) FROM appuser WHERE username = ?";
		Object[] params = { username };
		
		int count = jdbc.queryForObject(sql, Integer.class, params);
		
		return (count > 0) ? true : false;		
	}

	@Override
	public boolean update(AppUser appUser) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	public boolean delete(String username) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
		
	}

}