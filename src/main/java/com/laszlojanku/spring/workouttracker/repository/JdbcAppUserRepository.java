package com.laszlojanku.spring.workouttracker.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.model.AppUser;

/**
 * Repository using JdbcTemplate to manipulate AppUsers in the database.
 */
@Repository
public class JdbcAppUserRepository implements AppUserRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * Adds a new AppUser to the database.
	 * 
	 * @param	username	the new AppUser's name
	 * @param	password	the new AppUser's password
	 * @param	rolename	the new AppUser's rolename eg.: ROLE_USER or ROLE_ADMIN
	 * @return				the new AppUser's id
	 * @throws				DataAccessException on database error
	 */
	@Override
	public int add(String username, String password, String rolename) throws DataAccessException {
		String sql = "INSERT INTO appuser (username, password, rolename) VALUES (?, ?, ?)";
		
		jdbc.update(sql, username, password, rolename);
		
		return get(username).getId();
	}
	
	/**
	 * Gets an AppUser by username from the database.
	 * 
	 * @param	username	the username
	 * @return				an AppUser
	 * @throws				DataAccessException on database error
	 */
	@Override
	public AppUser get(String username) throws DataAccessException {
		// Return null if the user doesn't exists
		if (!isExists(username)) {
			return null;
		}
		
		String sql = "SELECT * FROM appuser WHERE username = ?";		
		
		Map<String, Object> map = jdbc.queryForMap(sql, username);

		return buildAppUserFromMap(map);
	}
	
	private AppUser buildAppUserFromMap(Map<String, Object> map) {	
		int id = (int)map.get("id");
		String username = (String)map.get("username");
		String password = (String)map.get("password");
		String rolename = (String)map.get("rolename");		
		
		return new AppUser(id, username, password, rolename);
	}
	
	/**
	 * Checks if a username exists in the database.
	 * 
	 * @param	username	the username to check
	 * @return				true if exists otherwise false
	 * @throws				DataAccessException on database error
	 */
	@Override
	public boolean isExists(String username) throws DataAccessException {
		String sql = "SELECT count(*) FROM appuser WHERE username = ?";
		
		int count = jdbc.queryForObject(sql, Integer.class, username);
		
		return (count > 0) ? true : false;		
	}
	
	/**
	 * Updates an AppUser.
	 * 
	 * @param	appUser	an AppUser object with the new details
	 * @return			true if successful
	 * @throws			DataAccessException on database error
	 */
	@Override
	public boolean update(AppUser appUser) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
		
	}
	
	/**
	 * Deletes an AppUser by username
	 * 
	 * @param	username	the username
	 * @return				true if successful
	 * @throws				DataAccessException on database error
	 */
	@Override
	public boolean delete(String username) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
		
	}
	
}