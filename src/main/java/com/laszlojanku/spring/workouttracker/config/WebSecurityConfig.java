package com.laszlojanku.spring.workouttracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().mvcMatchers("/secured/**").hasRole("USER");		
		
		// URL does not require login		
		http.authorizeRequests().mvcMatchers("/login").permitAll();
		http.authorizeRequests().mvcMatchers("/login/perform_login").permitAll();
		http.authorizeRequests().mvcMatchers("/logout").permitAll();
		http.authorizeRequests().mvcMatchers("/register").permitAll();
		
		http.authorizeRequests().mvcMatchers("/public/**").permitAll();
		// 
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		// Configure custom login form
		http.authorizeRequests().and().formLogin()
				.loginPage("/login"); 		// Set the login page URL				
		
		http.authorizeRequests().anyRequest().authenticated();
		
		return http.build();
	}

}
