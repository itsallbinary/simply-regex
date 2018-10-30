package com.itsallbinary.simplyregex.definition;

import com.itsallbinary.simplyregex.PatternAccumulator;
import com.itsallbinary.simplyregex.RegexHolder;

public class GroupDefinitionBuilder extends PatternAccumulator<GroupDefinition>
		implements DefinitionBuilder<GroupDefinition> {

	private String groupName;

	private LinkingGroupDefinitionBuilder linkingGroupDefinitionBuilder;

	public GroupDefinitionBuilder(RegexHolder regexHolder) {
		super(regexHolder);
	}

	public GroupDefinitionBuilder(String groupName, RegexHolder regexHolder) {
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

	public LinkingGroupDefinitionBuilder getLinkingGroupDefinitionBuilder() {
		return linkingGroupDefinitionBuilder;
	}

	@Override
	public GroupDefinition def() {
		return new GroupDefinition(groupName, this);
	}

}
