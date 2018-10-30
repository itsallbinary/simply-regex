package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.SimpleRegex.charThatIs;
import static com.itsallbinary.simplyregex.SimpleRegex.groupHaving;
import static com.itsallbinary.simplyregex.SimpleRegex.groupWithName;
import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.itsallbinary.simplyregex.SimpleRegex;

public class SimpleRegexCaptureTest {

	@Test
	public void test_abcDigit() {

		String builtRegex = SimpleRegex.regex().anywhereInText()
				.oneOrMoreOf(groupHaving().exactString("abc").then().anyDigitChar().build()).build();
		String testString = "xsxsxsabc1ppppppabc7ooooooooo";

		String[] expectedValue = new String[] { "abc1", "abc7" };

		findCaptureGroups(builtRegex, testString, expectedValue);

	}

	@Test
	public void test_nameCapture() {

		String builtRegex = SimpleRegex.regex().anywhereInText().exactString("My Name is ").then()
				.group(groupHaving().oneOrMoreOf(charThatIs().anyWordChar().build()).build()).then().exactString(". ")
				.build();
		String testString = "My Name is John. My Name is Merry. My Name is Rock. ";

		Matcher matcher = Pattern.compile(builtRegex).matcher(testString);
		matcher.find();
		assertEquals("John", matcher.group(1));
		matcher.find();
		assertEquals("Merry", matcher.group(1));
		matcher.find();
		assertEquals("Rock", matcher.group(1));

//		String[] expectedValue = new String[] { "John", "Merry", "Rock" };
//
//		findCaptureGroups(builtRegex, testString, expectedValue);

	}

	@Test
	public void test_nameCaptureWithNamedGroup() {

		String builtRegex = SimpleRegex.regex().anywhereInText().exactString("My Name is ").then()
				.group(groupWithName("personname").having().oneOrMoreOf(charThatIs().anyWordChar().build()).build())
				.then().exactString(". ").build();
		String testString = "My Name is John. My Name is Merry. My Name is Rock. ";

		Matcher matcher = Pattern.compile(builtRegex).matcher(testString);
		matcher.find();
		assertEquals("John", matcher.group("personname"));
		matcher.find();
		assertEquals("Merry", matcher.group("personname"));
		matcher.find();
		assertEquals("Rock", matcher.group("personname"));

	}

	private void findCaptureGroups(String builtRegex, String testString, String[] expectedValue) {

		System.out.println("Built Regex = " + builtRegex);

		Matcher matcher = Pattern.compile(builtRegex).matcher(testString);

		for (int i = 1; matcher.find(); i++) {
			String group = matcher.group(1);
			System.out.println("group " + i + " = " + group);
			assertEquals(expectedValue[i - 1], group);
		}

	}

}
