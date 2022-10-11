package com.laszlojanku.spring.workouttracker.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExerciseNameValidatorTest {
	
	@InjectMocks
	private ExerciseNameValidator exerciseNameValidator;
	
	@Test
	void validate_ContainsNumber_ReturnsFalse() {
		String name = "exercise1";
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);		
	}
	
	@ParameterizedTest
	@MethodSource("com.laszlojanku.spring.workouttracker.validator.SpecialCharsProvider#getSpecials")
	void validate_ContainsSpecialChars_ReturnFalse(String specialChar) {
		// Append the word "exercise" to the beginning
		String name = "exercise" + specialChar;
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
				
		assertFalse(actual);
	}
	
	// min 4, max 32
	
	@Test
	void validate_TooShort_ReturnsFalse() {
		String name = "exe"; // <4
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	void validate_TooLong_ReturnsFalse() {
		String name = "exerciseexerciseexerciseexercisee"; // >32
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void validate_NullOrEmpty_ReturnsFalse(String name) {
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	void validate_ContainsSpace_ReturnTrue() {
		String name = "exercise exercise exercise";
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertTrue(actual);
	}
	
	@Test
	void validate_ContainsSpaceAndNumbers_ReturnFalse() {
		String name = "exercise1 exercise2 exercise3";
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	void validate_ContainsCapitalLetters_ReturnTrue() {
		String name = "Exercise Exercise Exercise";
		
		boolean actual = exerciseNameValidator.validate(name).isValid();
		
		assertTrue(actual);
	}
}