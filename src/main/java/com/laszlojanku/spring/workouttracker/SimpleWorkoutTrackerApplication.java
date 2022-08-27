package com.laszlojanku.spring.workouttracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleWorkoutTrackerApplication {
	
	public static Logger logger = LoggerFactory.getLogger(SimpleWorkoutTrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SimpleWorkoutTrackerApplication.class, args);
	}

}
