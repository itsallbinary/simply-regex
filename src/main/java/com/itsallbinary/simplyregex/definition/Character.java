package com.itsallbinary.simplyregex.definition;

import static com.itsallbinary.simplyregex.utils.RegexUtils.quoteIfRequired;

public class Character {

	private enum CharType {
		CHAR_RANGE, EXACT_CHAR, WILD_CARD;
	}

	public enum WildCard {

		ANY_CHAR("."), ANY_STRING(".*"), ANY_DIGIT("\\d"), ANY_NON_DIGIT("\\D"), ANY_WHITE_SPACE("\\s"),
		ANY_NON_WHITE_SPACE("\\S"), ANY_WORD_CHAR("\\w"), ANY_NON_WORD_CHAR("\\W"), TAB("\t"), NEW_LINE("\n"),
		CARRIAGE_RETURN("\r"), FORM_FEED("\f"),

		/*
		 * Nice to have
		 */
		SPACE_CHAR(" "),

		/*
		 * POSIX;
		 */

		LOWERCASE_ALPHABET("\\p{Lower}"), UPPERCASE_ALPHABET("\\p{Upper}"), ANYCASE_ALPHABET("\\p{Alpha}"),
		ALPHA_NUMERIC("\\p{Alnum}");

		private String wildCardString;

		private WildCard(String wildCardString) {
			this.wildCardString = wildCardString;
		}

		protected String getWildCardString() {
			return wildCardString;
		}

	}

	private CharType charType;

	private char start;

	private char end;

	private char exact;

	private WildCard wildCard;

	private Character(char start, char end) {
		this.charType = CharType.CHAR_RANGE;
		this.start = start;
		this.end = end;
	}

	private Character(char exact) {
		this.charType = CharType.EXACT_CHAR;
		this.exact = exact;

	}

	private Character(WildCard wildCard) {
		this.charType = CharType.WILD_CARD;
		this.wildCard = wildCard;

	}

	static Character charBetween(char start, char end) {
		return new Character(start, end);
	}

	static Character exactChar(char exact) {
		return new Character(exact);
	}

	public static Character wildCardChar(WildCard wildCard) {
		return new Character(wildCard);

	}

	char getStart() {
		return start;
	}

	void setStart(char start) {
		this.start = start;
	}

	char getEnd() {
		return end;
	}

	void setEnd(char end) {
		this.end = end;
	}

	char getExact() {
		return exact;
	}

	void setExact(char exact) {
		this.exact = exact;
	}

	@Override
	public String toString() {
		if (CharType.EXACT_CHAR.equals(this.charType)) {
			return quoteIfRequired(this.exact);
		} else if (CharType.CHAR_RANGE.equals(this.charType)) {
			return quoteIfRequired(this.start) + "-" + quoteIfRequired(this.end);
		} else {
			return wildCard.getWildCardString();
		}
	}

}
