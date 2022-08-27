package com.laszlojanku.spring.workouttracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcExerciseHistoryRepository {
	
	@Autowired
	private JdbcTemplate jdbc;

}