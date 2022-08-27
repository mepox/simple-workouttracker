package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.repository.JdbcAppUserRepository;

@Service
public class AppUserService {
	
	@Autowired
	private JdbcAppUserRepository appUserRepository;

}