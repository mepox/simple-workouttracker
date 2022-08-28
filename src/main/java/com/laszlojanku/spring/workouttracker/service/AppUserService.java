package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.model.AppUser;
import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;

@Service
public class AppUserService {
	
	@Autowired
	private JdbcAppUserRepository appUserRepository;
	
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