package com.laszlojanku.spring.workouttracker.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExerciseNameValidatorTest {
	
	@InjectMocks
	private ExerciseNameValidator exerciseNameValidator;
	
	@Test
	public void validate_ContainsNumber_ReturnsFalse() {
		String name = "exercise1";
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);		
	}
	
	@ParameterizedTest
	@MethodSource("com.laszlojanku.spring.workouttracker.validator.SpecialCharsProvider#getSpecials")
	public void validate_ContainsSpecialChars_ReturnFalse(String specialChar) {
		// Append the word "user" to the beginning
		String name = "exercise" + specialChar;
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
				
		assertFalse(actual);
	}
	
	// min 4, max 32
	
	@Test
	public void validate_TooShort_ReturnsFalse() {
		String name = "exe"; // <4
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	public void validate_TooLong_ReturnsFalse() {
		String name = "exerciseexerciseexerciseexercisee"; // >32
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void validate_NullOrEmpty_ReturnsFalse(String name) {
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
}