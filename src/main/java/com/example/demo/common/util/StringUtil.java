package com.example.demo.common.util;

public class StringUtil {

	private static final String DELIMITER = "";

	public static String concatenateStrings(String... args) {
		return String.join(DELIMITER, args);
	}
}
