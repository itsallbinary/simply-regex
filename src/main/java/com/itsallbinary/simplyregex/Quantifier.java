package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.utils.RegexUtils.quoteIfRequired;

import com.itsallbinary.simplyregex.PatternAccumulator.LinkingPatternAccumulator;

public enum Quantifier {

	GREEDY("?", "*", "+", ""), RELUCTANT("??", "*?", "+?", "?"), POSSESSIVE("?+", "*+", "++", "+");

	private String onceNotAtAllSuffix;

	private String zeroOrMoreSuffix;

	private String oneOrMoreSuffix;

	private String multipleSuffix;

	private Quantifier(String onceNotAtAllSuffix, String zeroOrMoreSuffix, String oneOrMoreSuffix,
			String multipleSuffix) {
		this.onceNotAtAllSuffix = onceNotAtAllSuffix;
		this.zeroOrMoreSuffix = zeroOrMoreSuffix;
		this.oneOrMoreSuffix = oneOrMoreSuffix;
		this.multipleSuffix = multipleSuffix;
	}

	private String onceOrNotAtAlleOf(String input, boolean quote) {
		if (quote) {
			return quoteIfRequired(input) + this.onceNotAtAllSuffix;
		} else {
			return input + this.onceNotAtAllSuffix;
		}
	}

	public String onceOrNotAtAlleOf(String input) {
		return onceOrNotAtAlleOf(input, true);
	}

	public String onceOrNotAtAlleOf(char input) {
		return onceOrNotAtAlleOf("" + input, true);
	}

	public String onceOrNotAtAlleOf(CharacterDefinition characterDefinition) {
		return onceOrNotAtAlleOf(characterDefinition.buildChar(), false);
	}

	public String onceOrNotAtAlleOf(GroupDefinition groupDefinition) {
		return onceOrNotAtAlleOf(groupDefinition.buildGroup(), false);
	}

	private String zeroOrMoreOf(String input, boolean quote) {
		if (quote) {
			return quoteIfRequired(input) + this.zeroOrMoreSuffix;
		} else {
			return input + this.zeroOrMoreSuffix;
		}
	}

	public String zeroOrMoreOf(String input) {
		return zeroOrMoreOf(input, true);
	}

	public String zeroOrMoreOf(char input) {
		return zeroOrMoreOf("" + input, true);
	}

	public String zeroOrMoreOf(CharacterDefinition characterDefinition) {
		return zeroOrMoreOf(characterDefinition.buildChar(), false);
	}

	public String zeroOrMoreOf(GroupDefinition groupDefinition) {
		return zeroOrMoreOf(groupDefinition.buildGroup(), false);
	}

	private String oneOrMoreOf(String input, boolean quote) {
		if (quote) {
			return quoteIfRequired(input) + this.oneOrMoreSuffix;
		} else {
			return input + this.oneOrMoreSuffix;
		}
	}

	public String oneOrMoreOf(String input) {
		return oneOrMoreOf(input, true);
	}

	public String oneOrMoreOf(char input) {
		return oneOrMoreOf("" + input, true);
	}

	public String oneOrMoreOf(CharacterDefinition characterDefinition) {
		return oneOrMoreOf(characterDefinition.buildChar(), false);
	}

	public String oneOrMoreOf(GroupDefinition groupDefinition) {
		return oneOrMoreOf(groupDefinition.buildGroup(), false);
	}

	String getOnceNotAtAllSuffix() {
		return onceNotAtAllSuffix;
	}

	String getZeroOrMoreSuffix() {
		return zeroOrMoreSuffix;
	}

	String getOneOrMoreSuffix() {
		return oneOrMoreSuffix;
	}

	String getMultipleSuffix() {
		return multipleSuffix;
	}

}
