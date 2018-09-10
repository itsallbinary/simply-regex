package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.utils.RegexUtils.quoteIfRequired;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

	PatternAccumulator(/* T builder, */ RegexHolder regexHolder) {
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
	 * If input string contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * <pre>
	 * SimpleRegex.regex().startingWith().exactString("abc").build()
	 * </pre>
	 * 
	 * @param characters
	 * @return
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
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * <pre>
	 * SimpleRegex.regex().startingWith().oneOfTheCharacterBetween('d', 'f').build()
	 * </pre>
	 * 
	 * @param characters
	 * @return
	 */
	public LinkingPatternAccumulator oneOfTheCharacters(char... characters) {

		regexHolder.addNext("[" + quoteIfRequired(new String(characters)) + "]");
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one of the characters from the input character
	 * definition. Pattern created using "|" i.e. OR condition in regex within
	 * character class.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * <pre>
	 * SimpleRegex.regex().anywhereInText()<br/>.oneOrMoreOf(SimpleRegexDefinitions.charThatIs().between('a', 'c').or().exact('r').build()).build()
	 * </pre>
	 * 
	 * @param characters
	 * @return
	 */
	public LinkingPatternAccumulator oneOfTheCharacters(CharacterDefinition characterDefinition) {

		regexHolder.addNext(characterDefinition.buildChar());
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
	 * ----------------- Special Character wild cards ------------------
	 */

	public LinkingPatternAccumulator anyCharacter() {

		regexHolder.addNext(Character.anyCharacter().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyString() {

		regexHolder.addNext(Character.anyString().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyDigitChar() {

		regexHolder.addNext(Character.anyDigitChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyNonDigitChar() {

		regexHolder.addNext(Character.anyNonDigitChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyWhiteSpaceChar() {

		regexHolder.addNext(Character.anyWhiteSpaceChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyNonWhiteSpaceChar() {

		regexHolder.addNext(Character.anyNonWhiteSpaceChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyWordChar() {

		regexHolder.addNext(Character.anyWordChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator anyNonWordChar() {

		regexHolder.addNext(Character.anyNonWordChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator tabChar() {

		regexHolder.addNext(Character.tabChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator newLineChar() {

		regexHolder.addNext(Character.newLineChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator carriageReturnChar() {

		regexHolder.addNext(Character.carriageReturnChar().toString());
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator formFeedChar() {

		regexHolder.addNext(Character.formFeedChar().toString());
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
		regexHolder.addNext(groupDefinition.buildGroup());
		return linkingPatternAccumulator;
	}

	/*
	 * ----------------- Quantifier Methods ------------------
	 */

	/**
	 * Pattern to match zero or more occurrences of give character. Regex greedy
	 * quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(char character) {
		regexHolder.addNext(Quantifier.GREEDY.zeroOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given character definition.
	 * Regex greedy quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.zeroOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given group definition.
	 * Regex greedy quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.zeroOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of give character.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(Quantifier quantifier, char character) {
		regexHolder.addNext(quantifier.zeroOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given character definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(Quantifier quantifier, CharacterDefinition characterDefinition) {
		regexHolder.addNext(quantifier.zeroOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given group definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(Quantifier quantifier, GroupDefinition groupDefinition) {
		regexHolder.addNext(quantifier.zeroOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character. Regex greedy
	 * quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(char character) {
		regexHolder.addNext(Quantifier.GREEDY.oneOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character definition.
	 * Regex greedy quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.oneOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give group definition. Regex
	 * greedy quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.oneOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(Quantifier quantifier, char character) {
		regexHolder.addNext(quantifier.oneOrMoreOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(Quantifier quantifier, CharacterDefinition characterDefinition) {
		regexHolder.addNext(quantifier.oneOrMoreOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give group definition.
	 * {@link Quantifier} parameter passed will determine the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(Quantifier quantifier, GroupDefinition groupDefinition) {
		regexHolder.addNext(quantifier.oneOrMoreOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given
	 * character. Regex greedy quantifier "?" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(char character) {
		regexHolder.addNext(Quantifier.GREEDY.onceOrNotAtAlleOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given
	 * character definition. Regex greedy quantifier "?" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.onceOrNotAtAlleOf(characterDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given group
	 * definition. Regex greedy quantifier "?" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(Quantifier.GREEDY.onceOrNotAtAlleOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given
	 * character. {@link Quantifier} parameter passed will determine the regex
	 * created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(Quantifier quantifier, char character) {
		regexHolder.addNext(quantifier.onceOrNotAtAlleOf(character));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one occurrence or no occurrence at all of given
	 * character definition. {@link Quantifier} parameter passed will determine
	 * the regex created.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
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
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @param quantifier
	 * @return
	 */
	public LinkingPatternAccumulator onceOrNotAtAlleOf(Quantifier quantifier, GroupDefinition groupDefinition) {
		regexHolder.addNext(quantifier.onceOrNotAtAlleOf(groupDefinition));
		return linkingPatternAccumulator;
	}

	/*
	 * ----------------- Multiples of Methods ------------------
	 */

	public QuantifierLinkingPatternAccumulator exact(int times) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.EXACT, times, Quantifier.GREEDY);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator exact(int times, Quantifier quantifier) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.EXACT, times, quantifier);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator atleast(int times) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.AT_LEAST, times, Quantifier.GREEDY);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator atleast(int times, Quantifier quantifier) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.AT_LEAST, times, quantifier);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator between(int lowerLimit, int upperLimit) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.BETWEEN, lowerLimit, upperLimit, Quantifier.GREEDY);
		return quantifierLinkingPatternAccumulator;
	}

	public QuantifierLinkingPatternAccumulator between(int lowerLimit, int upperLimit, Quantifier quantifier) {

		QuantifierLinkingPatternAccumulator quantifierLinkingPatternAccumulator = new QuantifierLinkingPatternAccumulator(
				MultiplesType.BETWEEN, lowerLimit, upperLimit, quantifier);
		return quantifierLinkingPatternAccumulator;
	}

	// /**
	// * Pattern to match multiple occurrences of given character. Regex greedy
	// * quantifier {times} is used for this.
	// *
	// * If input character contains any special regex characters then it will
	// * escaped using {@link Pattern#quote(String)}
	// *
	// * @param input
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator exactMultiplesOf(char input, int times)
	// {
	// regexHolder.addNext(Quantifier.GREEDY.exactMultiplesOf(input, times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match multiple occurrences of given character definition.
	// * Regex greedy quantifier {times} is used for this.
	// *
	// * If input character contains any special regex characters then it will
	// * escaped using {@link Pattern#quote(String)}
	// *
	// * @param characterDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator exactMultiplesOf(CharacterDefinition
	// characterDefinition, int times) {
	// regexHolder.addNext(Quantifier.GREEDY.exactMultiplesOf(characterDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match multiple occurrences of given group definition. Regex
	// * greedy quantifier {times} is used for this.
	// *
	// * If input character contains any special regex characters then it will
	// * escaped using {@link Pattern#quote(String)}
	// *
	// * @param groupDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator exactMultiplesOf(GroupDefinition
	// groupDefinition, int times) {
	// regexHolder.addNext(Quantifier.GREEDY.exactMultiplesOf(groupDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match multiple occurrences of given character. Regex
	// * quantifier used for this is determined by passed quantifier.
	// *
	// * @param quantifier
	// * @param character
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator exactMultiplesOf(Quantifier quantifier,
	// char character, int times) {
	// regexHolder.addNext(quantifier.exactMultiplesOf(character, times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match multiple occurrences of given character definition.
	// * Regex quantifier used for this is determined by passed quantifier.
	// *
	// * @param quantifier
	// * @param characterDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator exactMultiplesOf(Quantifier quantifier,
	// CharacterDefinition characterDefinition,
	// int times) {
	// regexHolder.addNext(quantifier.exactMultiplesOf(characterDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match multiple occurrences of given group definition. Regex
	// * quantifier used for this is determined by passed quantifier.
	// *
	// * @param quantifier
	// * @param groupDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator exactMultiplesOf(Quantifier quantifier,
	// GroupDefinition groupDefinition,
	// int times) {
	// regexHolder.addNext(quantifier.exactMultiplesOf(groupDefinition, times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match atleast times multiple occurrences of given character
	// .
	// * Regex greedy quantifier {times} is used for this.
	// *
	// * If input character contains any special regex characters then it will
	// * escaped using {@link Pattern#quote(String)}
	// *
	// * @param input
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator atleastMultiplesOf(char input, int
	// times) {
	// regexHolder.addNext(Quantifier.GREEDY.atleastMultiplesOf(input, times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match atleast times multiple occurrences of given character
	// * definition. Regex greedy quantifier {times} is used for this.
	// *
	// * If input character contains any special regex characters then it will
	// * escaped using {@link Pattern#quote(String)}
	// *
	// * @param characterDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator atleastMultiplesOf(CharacterDefinition
	// characterDefinition, int times) {
	// regexHolder.addNext(Quantifier.GREEDY.atleastMultiplesOf(characterDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match atleast times multiple occurrences of given group
	// * definition. Regex greedy quantifier {times} is used for this.
	// *
	// * If input character contains any special regex characters then it will
	// * escaped using {@link Pattern#quote(String)}
	// *
	// * @param groupDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator atleastMultiplesOf(GroupDefinition
	// groupDefinition, int times) {
	// regexHolder.addNext(Quantifier.GREEDY.atleastMultiplesOf(groupDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match atleast times multiple occurrences of given character.
	// * Regex quantifier used for this is determined by passed quantifier.
	// *
	// * @param quantifier
	// * @param character
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator atleastMultiplesOf(Quantifier
	// quantifier, char character, int times) {
	// regexHolder.addNext(quantifier.atleastMultiplesOf(character, times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match atleast times multiple occurrences of given character
	// * definition. Regex quantifier used for this is determined by passed
	// * quantifier.
	// *
	// * @param quantifier
	// * @param characterDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator atleastMultiplesOf(Quantifier
	// quantifier, CharacterDefinition characterDefinition,
	// int times) {
	// regexHolder.addNext(quantifier.atleastMultiplesOf(characterDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }
	//
	// /**
	// * Pattern to match atleast times multiple occurrences of given group
	// * definition. Regex quantifier used for this is determined by passed
	// * quantifier.
	// *
	// * @param quantifier
	// * @param groupDefinition
	// * @param times
	// * @return
	// */
	// public LinkingPatternAccumulator atleastMultiplesOf(Quantifier
	// quantifier, GroupDefinition groupDefinition,
	// int times) {
	// regexHolder.addNext(quantifier.atleastMultiplesOf(groupDefinition,
	// times));
	// return linkingPatternAccumulator;
	// }

	/*
	 * ----------------- Other Methods ------------------
	 */

	abstract O build();

	RegexHolder getRegexHolder() {
		return regexHolder;
	}

	public class LinkingPatternAccumulator {

		private LinkingPatternAccumulator() {

		}

		/**
		 * This method links pattern before this method with pattern after this
		 * method in sequence of methods.
		 * 
		 * @return
		 */
		public PatternAccumulator<O> then() {
			return PatternAccumulator.this;
		}

		/**
		 * Pattern before this method will be joint with "|" i.e. OR regex
		 * condition with the pattern after this method.
		 * 
		 * @return
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

		public PatternAccumulator<O> occurrencesOf(char character) {
			getRegexHolder().addNext(multiplesOf("" + character, true));
			return PatternAccumulator.this;
		}

		public PatternAccumulator<O> occurrencesOf(CharacterDefinition characterDefinition) {
			getRegexHolder().addNext(multiplesOf(characterDefinition.buildChar(), false));
			return PatternAccumulator.this;
		}

		public PatternAccumulator<O> occurrencesOf(GroupDefinition groupDefinition) {
			getRegexHolder().addNext(multiplesOf(groupDefinition.buildGroup(), false));
			return PatternAccumulator.this;
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
