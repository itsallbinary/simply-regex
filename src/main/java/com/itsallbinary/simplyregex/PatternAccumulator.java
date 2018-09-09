package com.itsallbinary.simplyregex;

import static com.itsallbinary.simplyregex.SimpleRegexDefinitions.charThatIs;
import static com.itsallbinary.simplyregex.utils.RegexUtils.quoteIfRequired;

import java.util.Arrays;
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

	PatternAccumulator(/* T builder, */ RegexHolder regexHolder) {
		this.regexHolder = regexHolder;
		this.linkingPatternAccumulator = new LinkingPatternAccumulator();
		// this.builder = builder;
	}

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

	private String zeroOrMoreOf(String patternString) {
		return patternString + "*";
	}

	/**
	 * Pattern to match zero or more occurrences of give character. Regex
	 * quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(char character) {
		regexHolder.addNext(zeroOrMoreOf(quoteIfRequired(new String(new char[] { character }))));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given character definition.
	 * Regex quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(zeroOrMoreOf(characterDefinition.buildChar()));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match zero or more occurrences of given group definition.
	 * Regex quantifier "*" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator zeroOrMoreOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(zeroOrMoreOf(groupDefinition.buildGroup()));
		return linkingPatternAccumulator;
	}

	private String oneOrMoreOf(String patternString) {
		return patternString + "+";
	}

	/**
	 * Pattern to match one or more occurrences of give character. Regex
	 * quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(char character) {
		regexHolder.addNext(oneOrMoreOf(quoteIfRequired(new String(new char[] { character }))));
		return linkingPatternAccumulator;
	}

	/**
	 * Pattern to match one or more occurrences of give character definition.
	 * Regex quantifier "+" is used for this.
	 * 
	 * If input character contains any special regex characters then it will
	 * escaped using {@link Pattern#quote(String)}
	 * 
	 * @param character
	 * @return
	 */
	public LinkingPatternAccumulator oneOrMoreOf(CharacterDefinition characterDefinition) {
		regexHolder.addNext(oneOrMoreOf(characterDefinition.buildChar()));
		return linkingPatternAccumulator;
	}

	public LinkingPatternAccumulator oneOrMoreOf(GroupDefinition groupDefinition) {
		regexHolder.addNext(oneOrMoreOf(groupDefinition.buildGroup()));
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

	public LinkingPatternAccumulator group(GroupDefinition groupDefinition) {
		regexHolder.addNext("(" + groupDefinition.buildGroup() + ")");
		return linkingPatternAccumulator;
	}

	abstract O build();

	RegexHolder getRegexHolder() {
		return regexHolder;
	}

	public class LinkingPatternAccumulator {

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

}
