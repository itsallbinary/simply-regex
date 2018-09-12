package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.SimpleRegex.charThatIs;
import static com.itsallbinary.simplyregex.SimpleRegex.regex;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SimpleRegexSpecialTest {

	private static String EMAIL_REGEX = SimpleRegex.regex().startingWith().anyString().then().exactString("@").then()
			.anyString().then().exactString(".").then().oneOfTheStrings("com", "org", "gov").then().endOfText().build();

	private static String FIRST_M_LAST_NAME_REGEX = regex().startingWith().between(2, 20)
			.occurrencesOf(charThatIs().anyWordChar().build()).then().anyWhiteSpaceChar().then().exact(1)
			.occurrencesOf(charThatIs().anyWordChar().build()).then().anyWhiteSpaceChar().then().between(2, 25)
			.occurrencesOf(charThatIs().anyWordChar().build()).then().endOfText().build();

	@Parameters(name = "{index}: BuiltRegex = {0} | TestString = {1} | Expected = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { EMAIL_REGEX, "ravi.khar@something.com", true },
				{ EMAIL_REGEX, "ravi.khar@something.org", true }, { EMAIL_REGEX, "ravi.khar@something.gov", true },
				{ EMAIL_REGEX, "ravi.kharsomething.com", false }, { EMAIL_REGEX, "ravi.khar@somethingcom", false },
				{ EMAIL_REGEX, "123@something.com", true }, { EMAIL_REGEX, "ravi.khar@123.com", true },
				{ EMAIL_REGEX, "ravi.khar@something.bad", false },

				{ FIRST_M_LAST_NAME_REGEX, "dwayne D johnson", true },
				{ FIRST_M_LAST_NAME_REGEX, "Dwayne D Johnson", true },
				{ FIRST_M_LAST_NAME_REGEX, "Dwayne D Jjjjjjjjjjjjjjjjjjjjjjjjj", true },
				{ FIRST_M_LAST_NAME_REGEX, "D D Johnson", false },
				{ FIRST_M_LAST_NAME_REGEX, "Dwayne DD Johnson", false },
				{ FIRST_M_LAST_NAME_REGEX, "Dwayne D Jjjjjjjjjjjjjjjjjjjjjjjjjj", false },
				{ FIRST_M_LAST_NAME_REGEX, "Dwayne d", false },
				{ FIRST_M_LAST_NAME_REGEX, "Dwayne D Johnson zzz", false },
				{ FIRST_M_LAST_NAME_REGEX, "!@!@Dwayne D Johnson", false },

		});
	}

	@Parameter // first data value (0) is default
	public /* NOT private */ String builtRegex;

	@Parameter(1)
	public /* NOT private */ String testString;

	@Parameter(2)
	public boolean expectedResult;

	@Test
	public void testParameters() {

		System.out.println(EMAIL_REGEX);

		Pattern pattern = Pattern.compile(builtRegex);

		boolean isMatch = pattern.matcher(testString).matches();

		assertEquals(expectedResult, isMatch);
	}

}
