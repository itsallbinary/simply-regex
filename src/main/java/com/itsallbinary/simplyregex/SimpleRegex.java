package com.itsallbinary.simplyregex;

/**
 * Starting class to start building regex.
 * 
 * @author ravik
 *
 */
public class SimpleRegex {

	private StringRegexBuilder characterPattern;

	private RegexHolder regexHolder;

	private SimpleRegex() {
		this.regexHolder = new RegexHolder();
		this.characterPattern = new StringRegexBuilder(this.regexHolder);
	}

	public static SimpleRegex regex() {
		return new SimpleRegex();
	}

	public StringRegexBuilder startingWith() {
		regexHolder.addNext("^");
		return characterPattern;
	}

	public StringRegexBuilder anywhereInText() {

		return characterPattern;
	}

}
