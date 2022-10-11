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
class UsernameValidatorTest {
	
	@InjectMocks
	private UsernameValidator usernameValidator;
		
	@Test
	void validate_ContainsNumber_ReturnsFalse() {
		String username = "user123";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	void validate_ContainsSpace_ReturnsFalse() {
		String username = "user user";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@MethodSource("com.laszlojanku.spring.workouttracker.validator.SpecialCharsProvider#getSpecials")
	void validate_ContainsSpecialChars_ReturnFalse(String specialChar) {
		// Append the word "user" to the beginning
		String username = "user" + specialChar;
		
		boolean actual = usernameValidator.validate(username).isValid();
				
		assertFalse(actual);
	}
	
	@Test
	void validate_ValidUsername_ReturnsTrue() {
		String username = "user";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertTrue(actual);
	}
	
	// min 4, max 16
	
	@Test
	void validate_TooShort_ReturnsFalse() {
		String username = "use"; // <4
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@Test
	void validate_TooLong_ReturnsFalse() {
		String username = "useruseruseruserr"; // >16
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void validate_NullOrEmpty_ReturnsFalse(String username) {
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertFalse(actual);		
	}
	
	@Test
	void validate_ContainsCapitalLetters_ReturnsTrue() {
		String username = "UserUser";
		
		boolean actual = usernameValidator.validate(username).isValid();
		
		assertTrue(actual);
	}

}