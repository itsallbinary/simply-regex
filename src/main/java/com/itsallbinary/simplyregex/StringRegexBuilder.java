package com.itsallbinary.simplyregex;

public class StringRegexBuilder extends PatternAccumulator<String> {

	StringRegexBuilder(RegexHolder regexHolder) {
		super(regexHolder);
	}

	@Override
	public String build() {
		return getRegexHolder().build();
	}

}
