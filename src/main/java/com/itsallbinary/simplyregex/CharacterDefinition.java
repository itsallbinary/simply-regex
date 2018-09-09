package com.itsallbinary.simplyregex;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CharacterDefinition {

	private CharacterDefinitionBuilder characterDefinitionBuilder;

	CharacterDefinition(CharacterDefinitionBuilder characterDefinitionBuilder) {
		this.characterDefinitionBuilder = characterDefinitionBuilder;
	}

	String buildChar() {
		return "[" + Arrays.stream(characterDefinitionBuilder.getCharacters()).map(Character::toString)
				.collect(Collectors.joining()) + "]";
	}

}
