package com.itsallbinary.simplyregex.definition;

import com.itsallbinary.simplyregex.PatternAccumulator;
import com.itsallbinary.simplyregex.RegexHolder;

public class StringRegexBuilder extends PatternAccumulator<String> implements DefinitionBuilder<StringDefinition> {

	public StringRegexBuilder(RegexHolder regexHolder) {
		super(regexHolder);
	}

	@Override
	public StringDefinition def() {
		return new StringDefinition(getRegexHolder().build());
	}

	@Override
	protected String build() {
		return getRegexHolder().build();
	}

}
