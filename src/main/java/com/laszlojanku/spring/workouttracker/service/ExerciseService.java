package com.laszlojanku.spring.workouttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laszlojanku.spring.workouttracker.repository.JdbcExerciseRepository;

@Service
public class ExerciseService {
	
	@Autowired
	private JdbcExerciseRepository exerciseRepository;

}