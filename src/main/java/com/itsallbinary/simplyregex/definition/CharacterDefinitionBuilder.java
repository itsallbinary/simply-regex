package com.itsallbinary.simplyregex.definition;

import java.util.ArrayList;
import java.util.List;

public class CharacterDefinitionBuilder implements DefinitionBuilder<CharacterDefinition> {

	private List<Character> characters;

	private LinkingCharacterDefinitionBuilder linkingCharacterDefinitionBuilder;

	public CharacterDefinitionBuilder() {
		characters = new ArrayList<>();
		linkingCharacterDefinitionBuilder = new LinkingCharacterDefinitionBuilder();
	}

	public LinkingCharacterDefinitionBuilder between(char start, char end) {
		this.characters.add(Character.charBetween(start, end));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder exact(char exact) {
		this.characters.add(Character.exactChar(exact));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyCharacter() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_CHAR));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyString() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_STRING));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyDigitChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_DIGIT));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyNonDigitChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_NON_DIGIT));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyWhiteSpaceChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_WHITE_SPACE));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyNonWhiteSpaceChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_NON_WHITE_SPACE));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyWordChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_WORD_CHAR));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyNonWordChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANY_NON_WORD_CHAR));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder tabChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.TAB));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder newLineChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.NEW_LINE));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder carriageReturnChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.CARRIAGE_RETURN));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder formFeedChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.FORM_FEED));
		return linkingCharacterDefinitionBuilder;
	}

	/*
	 * POSIX character methods
	 */

	public LinkingCharacterDefinitionBuilder anyLowerCaseAlphaChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.LOWERCASE_ALPHABET));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyUpperCaseAlphaChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.UPPERCASE_ALPHABET));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyAlphaChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ANYCASE_ALPHABET));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyAlphaNumericChar() {
		this.characters.add(Character.wildCardChar(Character.WildCard.ALPHA_NUMERIC));
		return linkingCharacterDefinitionBuilder;
	}

	Character[] getCharacters() {
		return this.characters.toArray(new Character[this.characters.size()]);
	}

	public class LinkingCharacterDefinitionBuilder {

		/**
		 * CharacterDefinition before this method will be joint with "|" i.e. OR regex
		 * condition with the CharacterDefinition after this method.
		 * 
		 * @return
		 */
		public CharacterDefinitionBuilder or() {
			return CharacterDefinitionBuilder.this;
		}

		public CharacterDefinition build() {
			return CharacterDefinitionBuilder.this.def();
		};
	}

	@Override
	public CharacterDefinition def() {
		return new CharacterDefinition(this);
	}

}
