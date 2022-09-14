package com.laszlojanku.spring.workouttracker.validator;

import org.springframework.stereotype.Service;

/**
 * Validates an exercise name.
 * Exercise name can only contain alphabet characters and spaces. It has to be minimum 4 and maximum 32 characters.
 */
@Service
public class ExerciseNameValidator {
	
	private final String REGEX = "[ a-zA-Z]+";
	private final int MIN_LENGTH = 4;
	private final int MAX_LENGTH = 32;
	
	private final String INVALID_CHARS = "Exercise name can only contain alphabet characters and space.";
	private final String INVALID_LENGTH = "Exercise name has to be minimum 4 and maximum 32 characters.";	
	
	public ValidatorResponse validate(String exerciseName) {
		if (exerciseName == null) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		if (exerciseName.length() < MIN_LENGTH || exerciseName.length() > MAX_LENGTH) {
			return new ValidatorResponse(false, INVALID_LENGTH);
		}
		
		if (!exerciseName.matches(REGEX)) {
			return new ValidatorResponse(false, INVALID_CHARS);
		}
		
		return new ValidatorResponse(true, "OK");
	}

}