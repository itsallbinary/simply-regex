package com.itsallbinary.simplyregex.definition;

public class StringDefinition implements Definition {

	private String regex;

	public StringDefinition(String regex) {
		this.regex = regex;
	}

	@Override
	public String regex() {
		return regex;
	}

}
