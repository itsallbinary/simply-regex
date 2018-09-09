package com.itsallbinary.simplyregex;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class TempTest {
	
	//private String testRegex = "z(abc|def)z";

	private String testRegex = "[\\Qa\\E-\\Qc\\E]";

	@Test
	public void test() {
		Pattern pattern = Pattern.compile(testRegex,Pattern.MULTILINE);

		boolean isMatch = pattern.matcher("b").find();

		System.out.println(isMatch);
	}

}
