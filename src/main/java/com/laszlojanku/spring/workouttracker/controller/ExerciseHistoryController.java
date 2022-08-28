package com.laszlojanku.spring.workouttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;
import com.laszlojanku.spring.workouttracker.service.AppUserService;
import com.laszlojanku.spring.workouttracker.service.ExerciseHistoryService;

@RestController
public class ExerciseHistoryController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private ExerciseHistoryService exerciseHistoryService;
	
	@GetMapping("/user/history/all")
	public ResponseEntity<String> getAllByDate(Authentication auth) {
		return null;
	}
	
	@PostMapping("/user/history/add")
	public ResponseEntity<String> add(@RequestBody ExerciseHistory exerciseHistory, Authentication auth) {
		return null;
	}
	
	@DeleteMapping("/user/history/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id, Authentication auth) {
		return null;		
	}

}