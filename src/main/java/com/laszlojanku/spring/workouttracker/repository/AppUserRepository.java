package com.laszlojanku.spring.workouttracker.repository;

import com.laszlojanku.spring.workouttracker.model.AppUser;

public interface AppUserRepository {
	
	public void add(String username, String password, String rolename);
	public AppUser get(String username);
	public boolean isExists(String username);
	public void update(AppUser appUser);
	public void delete(String username);

}