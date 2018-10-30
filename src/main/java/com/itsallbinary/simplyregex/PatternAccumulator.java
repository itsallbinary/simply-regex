package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.utils.RegexUtils.quoteIfRequired;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.itsallbinary.simplyregex.definition.Character;
import com.itsallbinary.simplyregex.definition.CharacterDefinition;
import com.itsallbinary.simplyregex.definition.Definition;
import com.itsallbinary.simplyregex.definition.GroupDefinition;
import com.itsallbinary.simplyregex.utils.RegexUtils;

/**
 * This class accumulates all patterns which will be used to build entire
 * pattern.
 * 
 * @author ravik
 *
 * @param <O>
 */
public abstract class PatternAccumulator<O> {

	private RegexHolder regexHolder;

	private LinkingPatternAccumulator linkingPatternAccumulator;

	// private QuantifierLinkingPatternAccumulator
	// quantifierLinkingPatternAccumulator;

	protected PatternAccumulator(/* T builder, */ RegexHolder regexHolder) {
		this.regexHolder = regexHolder;
		this.linkingPatternAccumulator = new LinkingPatternAccumulator();
		// this.quantifierLinkingPatternAccumulator = new
		// QuantifierLinkingPatternAccumulator();
		// this.builder = builder;
	}

	/*
	 * ----------------- Basic Methods ------------------
	 */

	/**
	 * Pattern to match exact string.
	 * 
	 * If input string contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * <pre>
	 * SimpleRegex.regex().startingWith().exactString("abc").build()
	 * </pre>
	 * 
	 * @param characters - Exact string to match in pattern
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator exactString(String characters) {
		RegexUtils.checkInputForValidity(characters);
		regexHolder.addNext(quoteIfRequired(characters));
		return linkingPatternAccumulator;

	}

	/**
	 * Pattern to match one of the characters from the input characters. Pattern
	 * created using "|" i.e. OR condition in regex within character class.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * <pre>
	 * SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build()
	 * </pre>
	 * 
	 * @param characters - array of characters. One of these character will be
	 *                   matched using OR.
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOfTheCharacters(char... characters) {

		regexHolder.addNext("[" + quoteIfRequired(new String(characters)) + "]");
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one of the characters from the input character definition.
	 * Pattern created using "|" i.e. OR condition in regex within character class.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * <pre>
	 * SimpleRegex.regex().anywhereInText()
	 * 		.oneOrMoreOf(SimpleRegexDefinitions.charThatIs().between('a', 'c').or().exact('r').build()).build()
	 * </pre>
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOfTheCharacters(CharacterDefinition characterDefinition) {

		regexHolder.addNext(characterDefinition.regex());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator oneOfTheStrings(String... strings) {

		regexHolder.addNext(
				"(" + Arrays.stream(strings).map(s -> quoteIfRequired(s)).collect(Collectors.joining("|")) + ")");
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator oneOfTheCharacterBetween(char startChar, char endChar) {

		regexHolder.addNext("[" + quoteIfRequired("" + startChar) + "-" + quoteIfRequired("" + endChar) + "]");
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyCharacterExcept(char... characters) {

		regexHolder.addNext("[^" + quoteIfRequired(new String(characters)) + "]");
		return linkingPatternAccumulator;
	}

	/*
	 * Extension methods
	 */

	public LinkingPatternAccumulator def(Definition definition) {

		regexHolder.addNext(definition.regex());
		return linkingPatternAccumulator;
	}

	/*
	 * ----------------- Special Character wild cards ------------------
	 */

	public LinkingPatternAccumulator anyCharacter() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_CHAR).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyString() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_STRING).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyDigitChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_DIGIT).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyNonDigitChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_NON_DIGIT).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyWhiteSpaceChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_WHITE_SPACE).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyNonWhiteSpaceChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_NON_WHITE_SPACE).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyWordChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_WORD_CHAR).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyNonWordChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANY_NON_WORD_CHAR).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator tabChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.TAB).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator newLineChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.NEW_LINE).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator carriageReturnChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.CARRIAGE_RETURN).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator formFeedChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.FORM_FEED).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator spaceChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.SPACE_CHAR).toString());
		return linkingPatternAccumulator;
	}

	/*
	 * POSIX methods
	 */

	public LinkingPatternAccumulator anyLowerCaseAlphaChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.LOWERCASE_ALPHABET).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyUpperCaseAlphaChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.UPPERCASE_ALPHABET).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyAlphaChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ANYCASE_ALPHABET).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyAlphaNumericChar() {

		regexHolder.addNext(Character.wildCardChar(Character.WildCard.ALPHA_NUMERIC).toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator endOfText() {
		regexHolder.addNext("$");
		return linkingPatternAccumulator;
	}

	/*
	 * ----------------- Definition Methods ------------------
	 */

	public LinkingPatternAccumulator group(GroupDefinition groupDefinition) {
		regexHolder.addNext(groupDefinition.regex());
		return linkingPatternAccumulator;
	}

	/*
	 * ----------------- Quantifier Methods ------------------
	 */

	/**
	 * Pattern to match zero or more occurrences of give character. Regex greedy
	 * quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param character - character
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(char character) {
		regexHolder.addNext(Quantifier.GREEDY.zeroOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given character definition.
	 * Regex greedy quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.zeroOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given group definition. Regex
	 * greedy quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param groupDefinition - Definition of group which can be built using
	 *                        SimpleRegex.groupHaving()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.zeroOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of give character.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param character  - character
	 * @param quantifier - Type from enum com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(Quantifier quantifier, char character) {
		regexHolder.addNext(quantifier.zeroOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given character definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @param quantifier          - Type from enum
	 *                            com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(Quantifier quantifier, CharacterDefinition characterDefinition) {
		regexHolder.addNext(quantifier.zeroOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given group definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param groupDefinition - Definition of group which can be built using
	 *                        SimpleRegex.groupHaving()
	 * @param quantifier      - Type from enum
	 *                        com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(Quantifier quantifier, GroupDefinition groupDefinition) {
		regexHolder.addNext(quantifier.zeroOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character. Regex greedy
	 * quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param character - character
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOrMoreOf(char character) {
		regexHolder.addNext(Quantifier.GREEDY.oneOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character definition. Regex
	 * greedy quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOrMoreOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.oneOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give group definition. Regex
	 * greedy quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param groupDefinition - Definition of group which can be built using
	 *                        SimpleRegex.groupHaving()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOrMoreOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.oneOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param character  - character
	 * @param quantifier - Type from enum com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOrMoreOf(Quantifier quantifier, char character) {
		regexHolder.addNext(quantifier.oneOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @param quantifier          - Type from enum
	 *                            com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOrMoreOf(Quantifier quantifier, CharacterDefinition characterDefinition) {
		regexHolder.addNext(quantifier.oneOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give group definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param groupDefinition - Definition of group which can be built using
	 *                        SimpleRegex.groupHaving()
	 * @param quantifier      - Type from enum
	 *                        com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator oneOrMoreOf(Quantifier quantifier, GroupDefinition groupDefinition) {
		regexHolder.addNext(quantifier.oneOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given character.
	 * Regex greedy quantifier "?" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param character - character
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(char character) {
		regexHolder.addNext(Quantifier.GREEDY.onceOrNotAtAlleOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given character
	 * definition. Regex greedy quantifier "?" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.onceOrNotAtAlleOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given group
	 * definition. Regex greedy quantifier "?" is used for this.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param groupDefinition - Definition of group which can be built using
	 *                        SimpleRegex.groupHaving()
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.onceOrNotAtAlleOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given character.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param character  - character
	 * @param quantifier - Type from enum com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(Quantifier quantifier, char character) {
		regexHolder.addNext(quantifier.onceOrNotAtAlleOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given character
	 * definition. {@link Quantifier} parameter passed will determine the regex
	 * created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param characterDefinition - Definition of character which can be built using
	 *                            SimpleRegex.charThatIs()
	 * @param quantifier          - Type from enum
	 *                            com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(Quantifier quantifier, CharacterDefinition characterDefinition) {
		regexHolder.addNext(quantifier.onceOrNotAtAlleOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given group
	 * definition. {@link Quantifier} parameter passed will determine the regex
	 * created.
	 * 
	 * If input character contains any special regex characters then it will escaped
	 * using {@link Pattern#quote(String)}
	 * 
	 * @param groupDefinition - Definition of group which can be built using
	 *                        SimpleRegex.groupHaving()
	 * @param quantifier      - Type from enum
	 *                        com.itsallbinary.simplyregex.Quantifier
	 * @return LinkingPatternAccumulator
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(Quantifier quantifier, GroupDefinition groupDefinition) {
		regexHolder.addNext(quantifier.onceOrNotAtAlleOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/*
	 * ----------------- Multiples of Methods ------------------
	 */

	public QuantifierLinkingPatternAccumulator occurancesExact(int times) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.EXACT, times, Quantifier.GREEDY);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator occurancesExact(int times, Quantifier quantifier) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.EXACT, times, quantifier);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator occurancesAtleast(int times) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.AT_LEAST, times, Quantifier.GREEDY);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator occurancesAtleast(int times, Quantifier quantifier) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.AT_LEAST, times, quantifier);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator occurancesBetween(int lowerLimit, int upperLimit) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.BETWEEN, lowerLimit, upperLimit, Quantifier.GREEDY);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator occurancesBetween(int lowerLimit, int upperLimit,
			Quantifier quantifier) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.BETWEEN, lowerLimit, upperLimit, quantifier);
		return quantifierLinkingPatternAccumulator;
	}

	/*
	 * ----------------- Other Methods ------------------
	 */

	protected abstract O build();

	public RegexHolder getRegexHolder() {
		return regexHolder;
	}

	public class LinkingPatternAccumulator {

		private LinkingPatternAccumulator() {

		}

		/**
		 * This method links pattern before this method with pattern after this method
		 * in sequence of methods.
		 * 
		 * @return PatternAccumulator
		 */
		public PatternAccumulator<O> then() {
			return PatternAccumulator.this;
		}

		/**
		 * Pattern before this method will be joint with "|" i.e. OR regex condition
		 * with the pattern after this method.
		 * 
		 * @return PatternAccumulator
		 */
		public PatternAccumulator<O> or() {
			getRegexHolder().addNext("|");
			return PatternAccumulator.this;
		}

		public O build() {
			return PatternAccumulator.this.build();
		};
	}

	private enum MultiplesType {
		EXACT, AT_LEAST, BETWEEN;
	}

	public class QuantifierLinkingPatternAccumulator {

		private MultiplesType multiplesType;
		private Quantifier quantifier;
		private Integer lowerLimit;
		private Integer upperLimit;

		private QuantifierLinkingPatternAccumulator(MultiplesType multiplesType, Integer lowerLimit, Integer upperLimit,
				Quantifier quantifier) {
			this.multiplesType = multiplesType;
			this.lowerLimit = lowerLimit;
			this.upperLimit = upperLimit;
			this.quantifier = quantifier;
		}

		private QuantifierLinkingPatternAccumulator(MultiplesType multiplesType, Integer lowerLimit,
				Quantifier quantifier) {
			this.multiplesType = multiplesType;
			this.lowerLimit = lowerLimit;
			this.quantifier = quantifier;
		}

		public LinkingPatternAccumulator of(char character) {
			getRegexHolder().addNext(multiplesOf("" + character, true));
			return PatternAccumulator.this.linkingPatternAccumulator;
		}

		public LinkingPatternAccumulator of(CharacterDefinition characterDefinition) {
			getRegexHolder().addNext(multiplesOf(characterDefinition.regex(), false));
			return PatternAccumulator.this.linkingPatternAccumulator;
		}

		public LinkingPatternAccumulator of(GroupDefinition groupDefinition) {
			getRegexHolder().addNext(multiplesOf(groupDefinition.regex(), false));
			return PatternAccumulator.this.linkingPatternAccumulator;
		}

		private String multiplesOf(String input, boolean quote) {
			switch (multiplesType) {
			case EXACT:
				return exactMultiplesOf(input, this.lowerLimit, quote);
			case AT_LEAST:
				return atleastMultiplesOf(input, this.lowerLimit, quote);
			case BETWEEN:
				return betweenMultiplesOf(input, this.lowerLimit, this.upperLimit, quote);
			default:
				return "";
			}
		}

		private String exactMultiplesOf(String input, int times, boolean quote) {
			if (quote) {
				return quoteIfRequired(input) + "{" + times + "}" + this.quantifier.getMultipleSuffix();
			} else {
				return input + "{" + times + "}" + this.quantifier.getMultipleSuffix();
			}
		}

		private String atleastMultiplesOf(String input, int times, boolean quote) {
			if (quote) {
				return quoteIfRequired(input) + "{" + times + ",}" + this.quantifier.getMultipleSuffix();
			} else {
				return input + "{" + times + ",}" + this.quantifier.getMultipleSuffix();
			}
		}

		private String betweenMultiplesOf(String input, int lowerCount, int upperCount, boolean quote) {
			if (quote) {
				return quoteIfRequired(input) + "{" + lowerCount + "," + upperCount + "}"
						+ this.quantifier.getMultipleSuffix();
			} else {
				return input + "{" + lowerCount + "," + upperCount + "}" + this.quantifier.getMultipleSuffix();
			}
		}

	}

}
