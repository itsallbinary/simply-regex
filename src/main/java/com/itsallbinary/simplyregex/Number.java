package com.itsallbinary.simplyregex;

import java.util.Arrays;

public class Number {

	public static void main(String[] args) {
		System.out.println(anyNumberBetween(0, 255));
	}


	public static String anyNumberBetween(int start, int end) {

		String finalRegex = "";

		// if (start < 9) {
		// finalRegex = finalRegex + "[" + start + "-";
		//
		// if (end < 9) {
		// finalRegex = finalRegex + end + "]";
		// } else {
		// finalRegex = finalRegex + "9]";
		// }
		// }

		char[] startChar = ("" + start).toCharArray();
		char[] endChar = ("" + end).toCharArray();

		print(startChar);
		print(endChar);

		if (start > end) {
			throw new IllegalArgumentException("Start greater than end");
		}
		
		if(startChar.length == endChar.length){
			
		}

		return finalRegex;

	}

	private static void print(char[] chararr) {
		System.out.println("Printing");
		for (char c : chararr) {
			System.out.println(c);

		}
	}

}
