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

	/**
	 * Start here to build regex.
	 * 
	 * @return
	 */
	public static SimpleRegex regex() {
		return new SimpleRegex();
	}

	/**
	 * Starting method for building {@link CharacterDefinition}
	 * 
	 * @return
	 */
	public static CharacterDefinitionBuilder charThatIs() {
		return new CharacterDefinitionBuilder();
	}

	/**
	 * Starting method for building {@link GroupDefinition}
	 * 
	 * @return
	 */
	public static GroupDefinitionBuilder groupHaving() {
		return new GroupDefinitionBuilder(new RegexHolder());

	}

	public StringRegexBuilder startingWith() {
		regexHolder.addNext("^");
		return characterPattern;
	}

	public StringRegexBuilder anywhereInText() {

		return characterPattern;
	}

}
