package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.service.AppUserService;
import com.laszlojanku.spring.workouttracker.service.ExerciseHistoryService;
import com.laszlojanku.spring.workouttracker.service.ExerciseService;

@RestController
public class HomeController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private ExerciseHistoryService exerciseHistoryService;	

}