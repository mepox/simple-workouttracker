package com.laszlojanku.spring.workouttracker.validator;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {
	
	private final String REGEX = "[a-zA-Z0-9]+";
	private final int MIN_LENGTH = 4;
	private final int MAX_LENGTH = 16;
	
	private final String INVALID_CHARS = "Password can only contain alphanumeric characters.";
	private final String INVALID_LENGTH = "Password has to be minimum 4 and maximum 16 characters.";	
	
	public ValidatorResponse validate(String password) {
		if (password.length() < MIN_LENGTH | password.length() > MAX_LENGTH) {
			return new ValidatorResponse(false, INVALID_LENGTH);
		}
		
		if (!password.matches(REGEX)) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		return new ValidatorResponse(true, "OK");
	}

}