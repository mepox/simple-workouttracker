package com.laszlojanku.spring.workouttracker.validator;

import java.util.stream.Stream;

public class SpecialCharsProvider {
	
	// Special chars in one string
	private final static String specials = "~!@#$%^&*()-_=+[]\\{}|;':\",./<>?";
	
	public static Stream<String> getSpecials() {
		// Split them into a String[] array		
		String[] specialsArray = specials.split("");
		
		return Stream.of(specialsArray);
	}

}