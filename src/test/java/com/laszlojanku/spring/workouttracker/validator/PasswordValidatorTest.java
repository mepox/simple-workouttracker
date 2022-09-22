package com.laszlojanku.spring.workouttracker.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordValidatorTest {
	
	@InjectMocks
	private PasswordValidator passwordValidator;
	
	@Test
	public void validate_ContainsSpace_ReturnsFalse() {
		String password = "pass pass";
		
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@MethodSource("com.laszlojanku.spring.workouttracker.validator.SpecialCharsProvider#getSpecials")
	public void validate_ContainsSpecial_ReturnsFalse(String specialChar) {
		String password = "password" + specialChar;		
		
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	public void validate_ValidPassword_ReturnsTrue() {
		String password = "password123";
		
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertTrue(actual);
	}
	
	// min 4, max 16
	
	@Test
	public void validate_TooShort_ReturnsFalse() {
		String password = "pas"; // <4
		
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	public void validate_TooLong_ReturnsFalse() {
		String password = "passwordpasswordp"; // >16
		
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void validate_NullOrEmpty_ReturnsFalse(String password) {
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertFalse(actual);			
	}
	
	@Test
	public void validate_ContainsCapitalLetters_ReturnTrue() {
		String password = "PassWord123";
		
		boolean actual = passwordValidator.validate(password).isValid();
		
		assertTrue(actual);
	}

}