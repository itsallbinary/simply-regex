package com.itsallbinary.simplyregex;

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
public class SimpleRegexCombinationTest {

	@Parameters(name = "{index}: BuiltRegex = {0} | TestString = {1} | Expected = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ SimpleRegex.regex().startingWith().exactString("abc").then().exactString("def").build(),
						"abcdef", true },

				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacters('d', 'e', 'f')
						.build(), "abcd", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacters('d', 'e', 'f')
						.build(), "abce", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacters('d', 'e', 'f')
						.build(), "abcf", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacters('d', 'e', 'f')
						.build(), "abcdef", false },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacters('d', 'e', 'f')
						.build(), "abcg", false },

				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacterBetween('d', 'f')
						.build(), "abcd", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacterBetween('d', 'f')
						.build(), "abce", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacterBetween('d', 'f')
						.build(), "abcf", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacterBetween('d', 'f')
						.build(), "abcg", false },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacterBetween('d', 'f')
						.build(), "abca", false },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().oneOfTheCharacterBetween('d', 'f')
						.build(), "abc ", false },

				{ SimpleRegex.regex().startingWith().exactString("abc").then().anyCharacterExcept('d', 'e')
						.build(), "abcg", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().anyCharacterExcept('d', 'e')
						.build(), "abcd", false },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().anyCharacterExcept('d', 'e')
						.build(), "abce", false },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().anyCharacterExcept('d', 'e')
						.build(), "abzd", false },

				{ SimpleRegex.regex().startingWith().exactString("abc").then().anyCharacter().build(), "abc-",
						true },
				{ SimpleRegex.regex().startingWith().exactString("abc").then().anyCharacter().build(), "abc ",
						true },
				{ SimpleRegex.regex().startingWith().exactString("abc").or().exactString("def").build(),
						"abc", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").or().exactString("def").build(),
						"def", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").or().exactString("def").build(),
						"abcdef", false } });
	}

	@Parameter // first data value (0) is default
	public /* NOT private */ String builtRegex;

	@Parameter(1)
	public /* NOT private */ String testString;

	@Parameter(2)
	public boolean expectedResult;

	@Test
	public void testParameters() {

		Pattern pattern = Pattern.compile(builtRegex);

		boolean isMatch = pattern.matcher(testString).matches();

		assertEquals(expectedResult, isMatch);
	}

}
