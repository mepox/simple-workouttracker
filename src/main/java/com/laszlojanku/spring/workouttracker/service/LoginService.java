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

import com.laszlojanku.spring.workouttracker.exception.AppException;
import com.laszlojanku.spring.workouttracker.exception.JdbcException;
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
	 * @throws AppException
	 * @throws JdbcException
	 */
	public void login(LoginForm loginForm) throws AppException, JdbcException {
		AppUser appUser;
		
		try {
			appUser = appUserRepository.get(loginForm.getUsername());
		}
		catch (DataAccessException e) {
			throw new JdbcException("Database error.");
		}
		
		if (appUser == null) {
			throw new AppException("Username not found.");
		}
		
		if (!appUser.getPassword().equals(loginForm.getPassword())) {	
			throw new AppException("Incorrect password.");			
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