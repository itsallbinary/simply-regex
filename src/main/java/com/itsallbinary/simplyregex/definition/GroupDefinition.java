package com.itsallbinary.simplyregex.definition;

public class GroupDefinition implements Definition {

	private GroupDefinitionBuilder groupDefinitionBuilder;

	private String groupName;

	GroupDefinition(String groupName, GroupDefinitionBuilder groupDefinitionBuilder) {
		this.groupDefinitionBuilder = groupDefinitionBuilder;
		this.groupName = groupName;
	}

	@Override
	public String regex() {
		if (groupName == null) {
			return "(" + groupDefinitionBuilder.getRegexHolder().build() + ")";
		} else {
			return "(?<" + groupName + ">" + groupDefinitionBuilder.getRegexHolder().build() + ")";

		}
	}

}
