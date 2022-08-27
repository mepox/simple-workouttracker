package com.laszlojanku.spring.workouttracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laszlojanku.spring.workouttracker.model.AppUser;

@Repository
public class JdbcAppUserRepository implements AppUserRepository {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void add(String username, String password, String rolename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppUser get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(AppUser appUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		
	}

}