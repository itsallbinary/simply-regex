package com.itsallbinary.simplyregex.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RegexUtilsTest {
	// "<", "(", "[", "{", "\\", "^", "-", "=", "$", "!",
	// "|", "]", "}", ")", "?", "*", "+", ".", ">"
	@Parameters(name = "{index}: input = {0} | output = {1} ")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "a<b", "\\Qa<b\\E" }, { "a(b", "\\Qa(b\\E" }, { "a[b", "\\Qa[b\\E" },
				{ "a{b", "\\Qa{b\\E" }, { "a\\b", "\\Qa\\b\\E" }, { "a^b", "\\Qa^b\\E" }, { "a-b", "\\Qa-b\\E" },
				{ "a=b", "\\Qa=b\\E" }, { "a$b", "\\Qa$b\\E" }, { "a!b", "\\Qa!b\\E" }, { "a|b", "\\Qa|b\\E" },
				{ "a]b", "\\Qa]b\\E" }, { "a}b", "\\Qa}b\\E" }, { "a)b", "\\Qa)b\\E" }, { "a?b", "\\Qa?b\\E" },
				{ "a*b", "\\Qa*b\\E" }, { "a+b", "\\Qa+b\\E" }, { "a.b", "\\Qa.b\\E" }, { "a>b", "\\Qa>b\\E" } });
	}

	@Parameter // first data value (0) is default
	public /* NOT private */ String input;

	@Parameter(1)
	public /* NOT private */ String output;

	@Test
	public void testParameters() {

		String actual = RegexUtils.quoteIfRequired(input);
		System.out.println("input = " + input + " expected = " + output + " actual = " + actual);

		assertEquals(output, actual);
	}

}
