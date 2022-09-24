package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
import com.laszlojanku.spring.workouttracker.model.AppUser;
import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;

/**
 * Provides a service to manipulate AppUsers in the database.
 */
@Service
public class AppUserService {
	
	@Autowired
	private JdbcAppUserRepository appUserRepository;
	
	/**
	 * Gets an AppUser's id by username from the database.
	 * @param	username	username
	 * @return				AppUser's id
	 * @throws AppException
	 * @throws JdbcException
	 */
	public int getId(String username) throws AppException, JdbcException {
		AppUser appUser = null;
		
		try {
			appUser = appUserRepository.get(username);					
		} catch (DataAccessException e) {
			throw new JdbcException("Database error. Couldn't get the user.");
		}
		
		if (appUser == null) {
			throw new AppException("Username not found.");
		}
		
		return appUser.getId();
	}
	
	/**
	 * Gets an AppUser by username from the database.
	 * @param username	username
	 * @return			AppUser 
	 * @throws AppException
	 * @throws JdbcException
	 */	
	public AppUser getAppUser(String username) throws AppException, JdbcException {
		AppUser appUser = null;
		
		try {
			appUser = appUserRepository.get(username);					
		} catch (DataAccessException e) {
			throw new JdbcException("Database error. Couldn't get the user.");
		}
		
		if (appUser == null) {
			throw new AppException("Username not found.");
		}
		
		return appUser;
	}
	
	/**
	 * Calls the repository and checks if a username already exists or not.
	 * @param username	username
	 * @return			true if exists otherwise false
	 */	
	public boolean isExists(String username) {
		return appUserRepository.isExists(username);
	}
	
	/**
	 * Adds a new AppUser to the database.
	 * @param username
	 * @param password
	 * @param rolename
	 * @return the new AppUser's ID
	 */	
	public int add(String username, String password, String rolename) {
		int userId = appUserRepository.add(username, password, rolename);
		return userId;
	}

}