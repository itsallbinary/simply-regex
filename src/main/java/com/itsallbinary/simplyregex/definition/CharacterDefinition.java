package com.itsallbinary.simplyregex.definition;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CharacterDefinition implements Definition {

	private CharacterDefinitionBuilder characterDefinitionBuilder;

	CharacterDefinition(CharacterDefinitionBuilder characterDefinitionBuilder) {
		this.characterDefinitionBuilder = characterDefinitionBuilder;
	}

	@Override
	public String regex() {
		return "[" + Arrays.stream(characterDefinitionBuilder.getCharacters()).map(Character::toString)
				.collect(Collectors.joining()) + "]";
	}

}
