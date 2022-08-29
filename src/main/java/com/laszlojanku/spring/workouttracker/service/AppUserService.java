package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
	 * @throws 				Exception if something went wrong
	 */
	public int getId(String username) throws Exception {
		AppUser appUser;
		
		try {
			appUser = appUserRepository.get(username);					
		} catch (DataAccessException e) {
			throw new Exception("Username not found.");
		}
		
		return appUser.getId();
	}

}