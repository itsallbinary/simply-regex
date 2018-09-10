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

	// private String exactMultiplesOf(String input, int times, boolean quote) {
	// if (quote) {
	// return quoteIfRequired(input) + "{" + times + "}" + this.multipleSuffix;
	// } else {
	// return input + "{" + times + "}" + this.multipleSuffix;
	// }
	// }
	//
	// public String exactMultiplesOf(String input, int times) {
	// return exactMultiplesOf(input, times, true);
	// }
	//
	// public String exactMultiplesOf(char input, int times) {
	// return exactMultiplesOf("" + input, times, true);
	// }
	//
	// public String exactMultiplesOf(CharacterDefinition characterDefinition,
	// int times) {
	// return exactMultiplesOf(characterDefinition.buildChar(), times, false);
	// }
	//
	// public String exactMultiplesOf(GroupDefinition groupDefinition, int
	// times) {
	// return exactMultiplesOf(groupDefinition.buildGroup(), times, false);
	// }
	//
	// private String atleastMultiplesOf(String input, int times, boolean quote)
	// {
	// if (quote) {
	// return quoteIfRequired(input) + "{" + times + ",}" + this.multipleSuffix;
	// } else {
	// return input + "{" + times + ",}" + this.multipleSuffix;
	// }
	// }
	//
	// public String atleastMultiplesOf(String input, int times) {
	// return atleastMultiplesOf(input, times, true);
	// }
	//
	// public String atleastMultiplesOf(char input, int times) {
	// return atleastMultiplesOf("" + input, times, true);
	// }
	//
	// public String atleastMultiplesOf(CharacterDefinition characterDefinition,
	// int times) {
	// return atleastMultiplesOf(characterDefinition.buildChar(), times, false);
	// }
	//
	// public String atleastMultiplesOf(GroupDefinition groupDefinition, int
	// times) {
	// return atleastMultiplesOf(groupDefinition.buildGroup(), times, false);
	// }
	//
	// private String betweenMultiplesOf(String input, int lowerCount, int
	// upperCount, boolean quote) {
	// if (quote) {
	// return quoteIfRequired(input) + "{" + lowerCount + "," + upperCount + "}"
	// + this.multipleSuffix;
	// } else {
	// return input + "{" + lowerCount + "," + upperCount + "}" +
	// this.multipleSuffix;
	// }
	// }
	//
	// public String betweenMultiplesOf(String input, int lowerCount, int
	// upperCount) {
	// return betweenMultiplesOf(input, lowerCount, upperCount, true);
	// }
	//
	// public String betweenMultiplesOf(char input, int lowerCount, int
	// upperCount) {
	// return betweenMultiplesOf("" + input, lowerCount, upperCount, true);
	// }
	//
	// public String betweenMultiplesOf(CharacterDefinition characterDefinition,
	// int lowerCount, int upperCount) {
	// return betweenMultiplesOf(characterDefinition.buildChar(), lowerCount,
	// upperCount, false);
	// }
	//
	// public String betweenMultiplesOf(GroupDefinition groupDefinition, int
	// lowerCount, int upperCount) {
	// return betweenMultiplesOf(groupDefinition.buildGroup(), lowerCount,
	// upperCount, false);
	// }

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
