package com.laszlojanku.spring.workouttracker.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.laszlojanku.spring.workouttracker.SimpleWorkoutTrackerApplication;
import com.laszlojanku.spring.workouttracker.model.RegisterForm;

/**
 * Run methods after the application started. 
 */
public class StartupService {
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private ExerciseHistoryService exerciseHistoryService;
	
	@EventListener(ApplicationReadyEvent.class)
	private void addDefaultUserAfterStartup() {
		// Add a default user
		try {
			// Register the default user
			int userId = registerService.register(new RegisterForm("user", "user", "user"));
			// Add few exercise history to the default user			
			exerciseHistoryService.add(userId, 1, 100, 12, LocalDate.now().toString());
			exerciseHistoryService.add(userId, 1, 100, 11, LocalDate.now().toString());
			exerciseHistoryService.add(userId, 1, 100, 10, LocalDate.now().toString());
			
			exerciseHistoryService.add(userId, 2, 60, 12, LocalDate.now().toString());
			exerciseHistoryService.add(userId, 2, 60, 11, LocalDate.now().toString());
			exerciseHistoryService.add(userId, 2, 60, 10, LocalDate.now().toString());
		} catch (Exception e) {			
			SimpleWorkoutTrackerApplication.logger.warn("Error while trying to add the default user(s): " + e.getMessage());			
		}
	}

}