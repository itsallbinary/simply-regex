package com.itsallbinary.simplyregex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterDefinitionBuilder {

	private List<Character> characters;

	private LinkingCharacterDefinitionBuilder linkingCharacterDefinitionBuilder;

	CharacterDefinitionBuilder() {
		characters = new ArrayList<>();
		linkingCharacterDefinitionBuilder = new LinkingCharacterDefinitionBuilder();
	}

	CharacterDefinition build() {
		return new CharacterDefinition(this);
	}

	/*
	 * static String characterClass(Character... characters) { return "[" +
	 * Arrays.stream(characters).map(Character::toString).collect(Collectors.
	 * joining()) + "]"; }
	 */

	// public static CharacterDefinitionBuilder charThatIs() {
	// return new CharacterDefinitionBuilder();
	// }

	public LinkingCharacterDefinitionBuilder between(char start, char end) {
		this.characters.add(Character.charBetween(start, end));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder exact(char exact) {
		this.characters.add(Character.exactChar(exact));
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyCharacter() {
		this.characters.add(Character.anyCharacter());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyString() {
		this.characters.add(Character.anyString());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyDigitChar() {
		this.characters.add(Character.anyDigitChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyNonDigitChar() {
		this.characters.add(Character.anyNonDigitChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyWhiteSpaceChar() {
		this.characters.add(Character.anyWhiteSpaceChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyNonWhiteSpaceChar() {
		this.characters.add(Character.anyNonWhiteSpaceChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyWordChar() {
		this.characters.add(Character.anyWordChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder anyNonWordChar() {
		this.characters.add(Character.anyNonWordChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder tabChar() {
		this.characters.add(Character.tabChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder newLineChar() {
		this.characters.add(Character.newLineChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder carriageReturnChar() {
		this.characters.add(Character.carriageReturnChar());
		return linkingCharacterDefinitionBuilder;
	}

	public LinkingCharacterDefinitionBuilder formFeedChar() {
		this.characters.add(Character.formFeedChar());
		return linkingCharacterDefinitionBuilder;
	}

	Character[] getCharacters() {
		return this.characters.toArray(new Character[this.characters.size()]);
	}

	public class LinkingCharacterDefinitionBuilder {

		/**
		 * CharacterDefinition before this method will be joint with "|" i.e. OR
		 * regex condition with the CharacterDefinition after this method.
		 * 
		 * @return
		 */
		public CharacterDefinitionBuilder or() {
			return CharacterDefinitionBuilder.this;
		}

		public CharacterDefinition build() {
			return CharacterDefinitionBuilder.this.build();
		};
	}

}
