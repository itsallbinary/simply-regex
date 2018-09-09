package com.itsallbinary.simplyregex;

public class GroupDefinition {

	private GroupDefinitionBuilder groupDefinitionBuilder;

	GroupDefinition(GroupDefinitionBuilder groupDefinitionBuilder) {
		this.groupDefinitionBuilder = groupDefinitionBuilder;
	}

	String buildGroup() {
		return "(" + groupDefinitionBuilder.getRegexHolder().build() + ")";
	}

}
