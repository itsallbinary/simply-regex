package com.itsallbinary.simplyregex;

public class GroupDefinitionBuilder extends PatternAccumulator<GroupDefinition> {

	private String groupName;

	private LinkingGroupDefinitionBuilder linkingGroupDefinitionBuilder;

	GroupDefinitionBuilder(RegexHolder regexHolder) {
		super(regexHolder);
	}

	GroupDefinitionBuilder(String groupName, RegexHolder regexHolder) {
		super(regexHolder);
		this.groupName = groupName;
		this.linkingGroupDefinitionBuilder = new LinkingGroupDefinitionBuilder();
	}

	@Override
	public GroupDefinition build() {
		return new GroupDefinition(groupName, this);
	}

	public class LinkingGroupDefinitionBuilder {

		/**
		 * This method links group pattern before this method with pattern after this
		 * method in sequence of methods.
		 * 
		 * @return PatternAccumulator
		 */
		public GroupDefinitionBuilder having() {
			return GroupDefinitionBuilder.this;
		}

	}

	LinkingGroupDefinitionBuilder getLinkingGroupDefinitionBuilder() {
		return linkingGroupDefinitionBuilder;
	}

}
