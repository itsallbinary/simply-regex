package com.itsallbinary.simplyregex.utils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RegexUtils {

	private static String[] regexSpecialCharacters = new String[] { "<", "(", "[", "{", "\\", "^", "-", "=", "$", "!",
			"|", "]", "}", ")", "?", "*", "+", ".", ">" };

	public static String quoteIfRequired(String input) {

		if (Arrays.stream(regexSpecialCharacters).map(c -> input.contains(c)).anyMatch(b -> Boolean.TRUE.equals(b))) {
			return Pattern.quote(input);
		}

		return input;

	}

	public static void checkInputForValidity(String string) {
		if (string == null || "".equals(string)) {
			throw new IllegalArgumentException("Argument is null or empty string for method "
					+ Thread.currentThread().getStackTrace()[2].getMethodName() + "(). Please correct.");
		}
	}

	public static String quoteIfRequired(char input) {
		return quoteIfRequired("" + input);
	}

}
