package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.SimpleRegexDefinitions.groupHaving;
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
public class SimpleRegexFindTest {

	@Parameters(name = "{index}: BuiltRegex = {0} | TestString = {1} | Expected = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {

				{ SimpleRegex.regex().startingWith().exactString("a").then().anyString().build(), "abc", true },
				{ SimpleRegex.regex().startingWith().exactString("a").then().anyString().build(), "zzzabc", false },

				{ SimpleRegex.regex().startingWith().exactString("start").then().anyDigitChar().then()
						.exactString("end").build(), "start3endcomplete", true },
				{ SimpleRegex.regex().startingWith().exactString("start").then().anyDigitChar().then()
						.exactString("end").then().endOfText().build(), "start3end", true },
				{ SimpleRegex.regex().startingWith().exactString("start").then().anyDigitChar().then()
						.exactString("end").then().endOfText().build(), "start3endcomplete", false },

				{ SimpleRegex.regex().anywhereInText().exactString("a").then().anyString().build(), "abc", true },
				{ SimpleRegex.regex().anywhereInText().exactString("a").then().anyString().build(), "zzzabc", true },

				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "a", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "aa", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "ba", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "b", true },

				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(
						groupHaving().exactString("abc").then().anyDigitChar().build()).build(), "abc1abc2", true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("abc").then().anyDigitChar().build()).build(), "abc1zzz",
						true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("abc").then().anyDigitChar().build()).build(), "abczzz",
						false },

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

		Pattern pattern = Pattern.compile(builtRegex);

		boolean found = pattern.matcher(testString).find();

		assertEquals(expectedResult, found);
	}

}
