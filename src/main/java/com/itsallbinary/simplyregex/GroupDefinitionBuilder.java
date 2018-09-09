package com.itsallbinary.simplyregex;

public class GroupDefinitionBuilder extends PatternAccumulator<GroupDefinition> {

	GroupDefinitionBuilder(RegexHolder regexHolder) {
		super(regexHolder);
	}

	@Override
	public GroupDefinition build() {
		return new GroupDefinition(this);
	}

}
