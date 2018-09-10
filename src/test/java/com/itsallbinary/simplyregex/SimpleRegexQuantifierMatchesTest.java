package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.SimpleRegex.charThatIs;
import static com.itsallbinary.simplyregex.SimpleRegex.groupHaving;
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
public class SimpleRegexQuantifierMatchesTest {

	@Parameters(name = "{index}: BuiltRegex = {0} | TestString = {1} | Expected = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				/*
				 * ---------- Zero or more --------------------
				 */

				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "a", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "aa", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf('a').build(), "aaaaaaaa", true },

				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf(charThatIs().exact('a').build()).build(), "",
						true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf(charThatIs().exact('a').build()).build(), "a",
						true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf(charThatIs().exact('a').build()).build(), "aa",
						true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf(charThatIs().between('a', 'c').build()).build(),
						"aaa", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf(charThatIs().between('a', 'c').build()).build(),
						"bbb", true },
				{ SimpleRegex.regex().anywhereInText().zeroOrMoreOf(charThatIs().between('a', 'c').build()).build(),
						"ccc", true },
				{ SimpleRegex.regex().anywhereInText()
						.zeroOrMoreOf(charThatIs().between('a', 'c').or().exact('r').build()).build(), "ccc", true },
				{ SimpleRegex.regex().anywhereInText()
						.zeroOrMoreOf(charThatIs().between('a', 'c').or().exact('r').build()).build(), "rrr", true },

				{ SimpleRegex.regex().anywhereInText()
						.zeroOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "", true },
				{ SimpleRegex.regex().anywhereInText()
						.zeroOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "a9",
						true },
				{ SimpleRegex.regex().anywhereInText()
						.zeroOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "a9a9a9",
						true },
				{ SimpleRegex.regex().anywhereInText()
						.zeroOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "b2",
						false },
				/*
				 * ---------- One or more --------------------
				 */

				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf('a').build(), "", false },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf('a').build(), "a", true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf('a').build(), "aa", true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf('a').build(), "aaaaaaaa", true },

				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().exact('a').build()).build(), "",
						false },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().exact('a').build()).build(), "a",
						true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().exact('a').build()).build(), "aa",
						true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().between('a', 'c').build()).build(),
						"aaa", true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(
								charThatIs().between('a', 'c').build())
						.build(), "bbb", true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().between('a', 'c').build()).build(),
						"ccc", true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(charThatIs().between('a', 'c').or().exact('r').build()).build(), "bbb", true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(charThatIs().between('a', 'c').or().exact('r').build()).build(), "rrr", true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().anyDigitChar().build()).build(), "111",
						true },
				{ SimpleRegex.regex().anywhereInText().oneOrMoreOf(charThatIs().anyDigitChar().build()).build(), "aaa",
						false },

				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "", false },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "a9",
						true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "a8a7a0",
						true },
				/*
				 * ---------- Once or not at all --------------------
				 */
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf('a').build(), "a", true },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf('a').build(), "", true },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf('a').build(), "aa", false },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf('a').build(), "b", false },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "a",
						false },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "b",
						true },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "c", true },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "d", true },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "", true },
				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "e", false },

				{ SimpleRegex.regex().anywhereInText().onceOrNotAtAlleOf(charThatIs().between('b', 'd').build())
						.build(), "ccc", false },
				{ SimpleRegex.regex()
						.anywhereInText().onceOrNotAtAlleOf(
								groupHaving().exactString("a").then().anyDigitChar().build())
						.build(), "a9", true },
				{ SimpleRegex.regex().anywhereInText()
						.onceOrNotAtAlleOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(), "",
						true },
				{ SimpleRegex.regex().anywhereInText()
						.onceOrNotAtAlleOf(groupHaving().exactString("a").then().anyDigitChar().build()).build(),
						"a9a5a0", false },

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

		boolean isMatch = pattern.matcher(testString).matches();

		assertEquals(expectedResult, isMatch);
	}

}
