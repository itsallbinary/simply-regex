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

import com.itsallbinary.simplyregex.SimpleRegex;

@RunWith(Parameterized.class)
public class SimpleRegexMatchesTest {

	@Parameters(name = "{index}: BuiltRegex = {0} | TestString = {1} | Expected = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {

				{ SimpleRegex.regex().startingWith().exactString("abc").build(), "abc", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").build(), "aabbcc", false },
				{ SimpleRegex.regex().startingWith().exactString("abc").build(), "ab c", false },
				{ SimpleRegex.regex().startingWith().exactString("def").build(), "def", true },

				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "d", true },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "e", true },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "f", true },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "a", false },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "8", false },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "abcdef", false },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacters('d', 'e', 'f').build(), "abcg", false },

				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "a",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "b",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "c",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "p",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "z",
						false },

				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "a",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "b",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "c",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "p",
						true },
				{ SimpleRegex.regex().startingWith()
						.oneOfTheCharacters(charThatIs().between('a', 'c').or().exact('p').build()).build(), "z",
						false },

				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("abc").then().anyDigitChar().build()).build(),
						"abc1abc2", true },
				{ SimpleRegex.regex().anywhereInText()
						.oneOrMoreOf(groupHaving().exactString("abc").then().anyDigitChar().build()).build(), "abc1zzz",
						false },

				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "abc", true },
				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "def", true },
				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "abcdef", false },
				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "abcd", false },
				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "a", false },
				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "d", false },
				{ SimpleRegex.regex().startingWith().oneOfTheStrings("abc", "def").build(), "abc ", false },

				{ SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build(), "d", true },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build(), "e", true },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build(), "f", true },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build(), "g", false },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build(), "a", false },
				{ SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build(), "abc ", false },

				{ SimpleRegex.regex().startingWith().anyCharacterExcept('d', 'e').build(), "g", true },
				{ SimpleRegex.regex().startingWith().anyCharacterExcept('d', 'e').build(), "d", false },
				{ SimpleRegex.regex().startingWith().anyCharacterExcept('d', 'e').build(), "e", false },
				{ SimpleRegex.regex().startingWith().anyCharacterExcept('d', 'e').build(), "z", true },

				{ SimpleRegex.regex().startingWith().anyCharacter().build(), "a", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().build(), "r", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().build(), "z", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().build(), "1", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().build(), "$", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().build(), ",", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().build(), " ", true },

				{ SimpleRegex.regex().startingWith().anyDigitChar().build(), "1", true },
				{ SimpleRegex.regex().startingWith().anyDigitChar().build(), "0", true },
				{ SimpleRegex.regex().startingWith().anyDigitChar().build(), "6", true },
				{ SimpleRegex.regex().startingWith().anyDigitChar().build(), "9", true },
				{ SimpleRegex.regex().startingWith().anyDigitChar().build(), "a", false },
				{ SimpleRegex.regex().startingWith().anyDigitChar().build(), ",", false },

				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), ",", true },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), "g", true },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), " ", true },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), "0", false },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), "5", false },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), "9", false },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), "01", false },
				{ SimpleRegex.regex().startingWith().anyNonDigitChar().build(), "ab", false },

				{ SimpleRegex.regex().startingWith().anyString().build(), "asf896.,m345'[", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().then().anyDigitChar().build(), "a9", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().then().anyDigitChar().build(), "g0", true },
				{ SimpleRegex.regex().startingWith().anyCharacter().then().anyDigitChar().build(), "t-", false },
				{ SimpleRegex.regex().startingWith().anyCharacter().then().anyDigitChar().build(), ",,", false },

				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), " ", true },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "	", true },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "\n", true },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "\t", true },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "\f", true },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "\r", true },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "a", false },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "h", false },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "1", false },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "|", false },
				{ SimpleRegex.regex().startingWith().anyWhiteSpaceChar().build(), "&", false },

				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), " ", false },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "	", false },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "\n", false },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "\t", false },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "\f", false },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "\r", false },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "a", true },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "h", true },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "1", true },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "|", true },
				{ SimpleRegex.regex().startingWith().anyNonWhiteSpaceChar().build(), "&", true },

				{ SimpleRegex.regex().startingWith().anyWordChar().build(), " ", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "	", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "\n", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "\t", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "\f", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "\r", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "a", true },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "h", true },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "1", true },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "%", false },
				{ SimpleRegex.regex().startingWith().anyWordChar().build(), "&", false },

				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), " ", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "	", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "\n", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "\t", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "\f", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "\r", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "a", false },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "h", false },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "1", false },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "8", false },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "%", true },
				{ SimpleRegex.regex().startingWith().anyNonWordChar().build(), "&", true },

				{ SimpleRegex.regex().startingWith().tabChar().build(), "	", true },
				{ SimpleRegex.regex().startingWith().tabChar().build(), "\t", true },
				{ SimpleRegex.regex().startingWith().tabChar().build(), "&", false },
				{ SimpleRegex.regex().startingWith().tabChar().build(), "r", false },
				{ SimpleRegex.regex().startingWith().tabChar().build(), "8", false },

				{ SimpleRegex.regex().startingWith().newLineChar().build(), "\n", true },
				{ SimpleRegex.regex().startingWith().newLineChar().build(), "&", false },
				{ SimpleRegex.regex().startingWith().newLineChar().build(), "r", false },
				{ SimpleRegex.regex().startingWith().newLineChar().build(), "8", false },

				{ SimpleRegex.regex().startingWith().carriageReturnChar().build(), "\r", true },
				{ SimpleRegex.regex().startingWith().carriageReturnChar().build(), "&", false },
				{ SimpleRegex.regex().startingWith().carriageReturnChar().build(), "r", false },
				{ SimpleRegex.regex().startingWith().carriageReturnChar().build(), "8", false },

				{ SimpleRegex.regex().startingWith().formFeedChar().build(), "\f", true },
				{ SimpleRegex.regex().startingWith().formFeedChar().build(), "&", false },
				{ SimpleRegex.regex().startingWith().formFeedChar().build(), "r", false },
				{ SimpleRegex.regex().startingWith().formFeedChar().build(), "8", false },

				/*
				 * POSIX
				 */

				{ SimpleRegex.regex().startingWith().anyLowerCaseAlphaChar().build(), "a", true },
				{ SimpleRegex.regex().startingWith().anyLowerCaseAlphaChar().build(), "g", true },
				{ SimpleRegex.regex().startingWith().anyLowerCaseAlphaChar().build(), "A", false },
				{ SimpleRegex.regex().startingWith().anyLowerCaseAlphaChar().build(), "1", false },
				{ SimpleRegex.regex().startingWith().anyLowerCaseAlphaChar().build(), "%", false },

				{ SimpleRegex.regex().startingWith().anyUpperCaseAlphaChar().build(), "a", false },
				{ SimpleRegex.regex().startingWith().anyUpperCaseAlphaChar().build(), "g", false },
				{ SimpleRegex.regex().startingWith().anyUpperCaseAlphaChar().build(), "A", true },
				{ SimpleRegex.regex().startingWith().anyUpperCaseAlphaChar().build(), "L", true },
				{ SimpleRegex.regex().startingWith().anyUpperCaseAlphaChar().build(), "1", false },
				{ SimpleRegex.regex().startingWith().anyUpperCaseAlphaChar().build(), "%", false },

				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "a", true },
				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "g", true },
				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "A", true },
				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "L", true },
				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "1", false },
				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "9", false },
				{ SimpleRegex.regex().startingWith().anyAlphaChar().build(), "%", false },

				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "a", true },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "g", true },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "A", true },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "L", true },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "1", true },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "9", true },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "%", false },
				{ SimpleRegex.regex().startingWith().anyAlphaNumericChar().build(), "&", false },

				{ SimpleRegex.regex().startingWith().exactString("abc").or().exactString("def").build(), "abc", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").or().exactString("def").build(), "def", true },
				{ SimpleRegex.regex().startingWith().exactString("abc").or().exactString("def").build(), "abcdef",
						false } });
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
