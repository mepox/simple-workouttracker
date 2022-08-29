package com.laszlojanku.spring.workouttracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.model.AppUser;
import com.laszlojanku.spring.workouttracker.model.LoginForm;
import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;

/**
 * Provides a service to handle the logging in process.
 */
@Service
public class LoginService {
	
	@Autowired
	private JdbcAppUserRepository appUserRepository;
	
	/**
	 * Log-in the user using the LoginForm.
	 * @param	loginForm	LoginForm object
	 * @throws 				Exception if something went wrong
	 */
	public void login(LoginForm loginForm) throws Exception {
		AppUser appUser;
		
		try {
			appUser = appUserRepository.get(loginForm.getUsername());
		}
		catch (DataAccessException e) {
			throw new Exception("Database error.");
		}
		
		if (appUser == null) {
			throw new Exception("Username not found.");
		}
		
		if (!appUser.getPassword().equals(loginForm.getPassword())) {	
			throw new Exception("Incorrect password.");			
		}
		
		// Username and password are correct, so now login the user
		
		// Create the GrantedAuthority list
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		// Add the role name
		grantList.add(new SimpleGrantedAuthority(appUser.getRolename()));		
		
		// Create a new auth
		Authentication auth = new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword(), grantList);		
		
		// Login the user
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);		
	}

}