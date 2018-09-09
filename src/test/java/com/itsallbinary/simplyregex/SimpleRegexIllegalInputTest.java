package com.itsallbinary.simplyregex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.regex.Pattern;

import org.junit.Test;

public class SimpleRegexIllegalInputTest {

	@Test
	public void testExactStringForException() {
		try {
			Pattern.compile(SimpleRegex.regex().startingWith().exactString(null).then().anyString().build());

			fail("Should have given IllegalArgumentException but did not cause excetion.");
		} catch (IllegalArgumentException ex) {
			assertEquals("Argument is null or empty string for method exactString(). Please correct.", ex.getMessage());
		} catch (Exception e) {
			fail("Should have given IllegalArgumentException but caused different exception - " + e);
		}

	}

}
