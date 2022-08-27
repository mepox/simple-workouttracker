package com.laszlojanku.spring.workouttracker.repository;

import com.laszlojanku.spring.workouttracker.model.AppUser;

public interface AppUserRepository {
	
	public int add(String username, String password, String rolename);
	public AppUser get(String username);
	public boolean isExists(String username);
	public boolean update(AppUser appUser);
	public boolean delete(String username);

}