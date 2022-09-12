package com.laszlojanku.spring.workouttracker.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsernameValidatorTest {
	
	@InjectMocks
	private UsernameValidator usernameValidator;
		
	@Test
	public void validate_ContainsNumber_ReturnsFalse() {
		String username = "user123";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	public void validate_ContainsSpace_ReturnsFalse() {
		String username = "user user";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@MethodSource("com.laszlojanku.spring.workouttracker.validator.SpecialCharsProvider#getSpecials")
	public void validate_ContainsSpecialChars_ReturnFalse(String specialChar) {
		// Append the word "user" to the beginning
		String username = "user" + specialChar;
		
		boolean actual = usernameValidator.validate(username).isValid();
				
		assertFalse(actual);
	}
	
	@Test
	public void validate_ValidUsername_ReturnsTrue() {
		String username = "user";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertTrue(actual);
	}
	
	// min 4, max 16
	
	@Test
	public void validate_TooShort_ReturnsFalse() {
		String username = "use"; // <4
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	public void validate_TooLong_ReturnsFalse() {
		String username = "useruseruseruserr"; // >16
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}

}