package com.laszlojanku.spring.workouttracker.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * Validates a password.
 * Password can only contain alphanumeric characters. It has to be minimum 4 and maximum 16 characters.
 */
@Service
public class PasswordValidator {
	
	private final String REGEX = "[a-zA-Z0-9]+";
	private final Pattern pattern;
	private Matcher matcher;
	private final int MIN_LENGTH = 4;
	private final int MAX_LENGTH = 16;
	
	private final String INVALID_CHARS = "Password can only contain alphanumeric characters.";
	private final String INVALID_LENGTH = "Password has to be minimum 4 and maximum 16 characters.";
	
	public PasswordValidator() {
		pattern = Pattern.compile(REGEX);
	}
	
	public ValidatorResponse validate(String password) {
		if (password == null) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
			return new ValidatorResponse(false, INVALID_LENGTH);
		}
		
		matcher = pattern.matcher(password);
		
		if (!matcher.matches()) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		return new ValidatorResponse(true, "OK");
	}

}