package com.itsallbinary.simplyregex;

public class SimpleRegexDefinitions {
	public static CharacterDefinitionBuilder charThatIs() {
		return new CharacterDefinitionBuilder();
	}

	public static GroupDefinitionBuilder groupHaving() {
		return new GroupDefinitionBuilder(new RegexHolder());

	}
}
