package com.laszlojanku.spring.workouttracker.exception;

public class JdbcException extends RuntimeException {
	
	public JdbcException(String message) {
		super(message);
	}
}
