package com.laszlojanku.spring.workouttracker.validator;

public class ValidatorResponse {
	
	private boolean valid;
	private String message;
	
	public ValidatorResponse(boolean valid, String message) {
		this.valid = valid;
		this.message = message;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getMessage() {
		return message;
	}

}