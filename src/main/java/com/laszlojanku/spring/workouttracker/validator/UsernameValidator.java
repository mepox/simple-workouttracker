package com.laszlojanku.spring.workouttracker.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * Validates a username.
 * Username can only contain alphabet characters. It has to be minimum 4 and maximum 16 characters.
 */
@Service
public class UsernameValidator {
	
	private final String REGEX = "[a-zA-Z]+";
	private final Pattern pattern;
	private Matcher matcher;
	private final int MIN_LENGTH = 4;
	private final int MAX_LENGTH = 16;
	
	private final String INVALID_CHARS = "Username can only contain alphabet characters.";
	private final String INVALID_LENGTH = "Username has to be minimum 4 and maximum 16 characters.";
	
	public UsernameValidator() {
		pattern = Pattern.compile(REGEX);
	}
	
	public ValidatorResponse validate(String username) {
		if (username == null) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		if (username.length() < MIN_LENGTH || username.length() > MAX_LENGTH) {
			return new ValidatorResponse(false, INVALID_LENGTH);
		}
		
		matcher = pattern.matcher(username);
		
		if (!matcher.matches()) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		return new ValidatorResponse(true, "OK");
	}
	
}