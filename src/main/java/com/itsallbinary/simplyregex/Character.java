package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.utils.RegexUtils.quoteIfRequired;

import java.util.regex.Pattern;

import com.itsallbinary.simplyregex.utils.RegexUtils;

public class Character {

	private enum CharType {
		CHAR_RANGE, EXACT_CHAR, WILD_CARD;
	}

	private enum WildCard {

		ANY_CHAR("."), ANY_STRING(".*"), ANY_DIGIT("\\d"), ANY_NON_DIGIT("\\D"), ANY_WHITE_SPACE(
				"\\s"), ANY_NON_WHITE_SPACE("\\S"), ANY_WORD_CHAR("\\w"), ANY_NON_WORD_CHAR(
						"\\W"), TAB("\t"), NEW_LINE("\n"), CARRIAGE_RETURN("\r"), FORM_FEED("\f");

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

	static Character anyCharacter() {

		return new Character(WildCard.ANY_CHAR);
	}

	static Character anyString() {

		return new Character(WildCard.ANY_STRING);
	}

	static Character anyDigitChar() {

		return new Character(WildCard.ANY_DIGIT);

	}

	static Character anyNonDigitChar() {

		return new Character(WildCard.ANY_NON_DIGIT);

	}

	static Character anyWhiteSpaceChar() {

		return new Character(WildCard.ANY_WHITE_SPACE);

	}

	static Character anyNonWhiteSpaceChar() {

		return new Character(WildCard.ANY_NON_WHITE_SPACE);

	}

	static Character anyWordChar() {

		return new Character(WildCard.ANY_WORD_CHAR);

	}

	static Character anyNonWordChar() {

		return new Character(WildCard.ANY_NON_WORD_CHAR);

	}

	static Character tabChar() {

		return new Character(WildCard.TAB);

	}

	static Character newLineChar() {

		return new Character(WildCard.NEW_LINE);

	}

	static Character carriageReturnChar() {

		return new Character(WildCard.CARRIAGE_RETURN);

	}

	static Character formFeedChar() {

		return new Character(WildCard.FORM_FEED);

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
