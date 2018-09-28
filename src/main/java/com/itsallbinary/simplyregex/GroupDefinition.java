package com.itsallbinary.simplyregex;

public class GroupDefinition {

	private GroupDefinitionBuilder groupDefinitionBuilder;

	private String groupName;

	GroupDefinition(String groupName, GroupDefinitionBuilder groupDefinitionBuilder) {
		this.groupDefinitionBuilder = groupDefinitionBuilder;
		this.groupName = groupName;
	}

	String buildGroup() {
		if (groupName == null) {
			return "(" + groupDefinitionBuilder.getRegexHolder().build() + ")";
		} else {
			return "(?<" + groupName + ">" + groupDefinitionBuilder.getRegexHolder().build() + ")";

		}
	}

}
