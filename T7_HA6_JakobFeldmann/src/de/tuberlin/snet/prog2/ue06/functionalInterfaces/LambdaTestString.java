package de.tuberlin.snet.prog2.ue06.functionalInterfaces;

public class LambdaTestString {

	public static String betterString(String a, String b, CompString c) {
		if (c.stringCompare(a, b) == true) {
			return a;
		} else {
			return b;
		}
	}

	public static void main(String[] args) {
		
		
		
		CompString first = (String a, String b) -> true;

		System.out.println(betterString("a", "b", first));

		
		
		CompString longer = (String a, String b) -> a.length() > b.length();

		System.out.println(betterString("abcd", "bc", longer));

		
		
		CompString bigLetters = (String a, String b) -> {

			int zhlra = 0;
			int zhlrb = 0;

			for (char x : a.toCharArray()) {
				if (Character.isUpperCase(x) == true) {
					++zhlra;
				}
			}
			for (char y : b.toCharArray()) {
				if (Character.isUpperCase(y) == true) {
					++zhlrb;
				}
			}

			return zhlra >= zhlrb;
		};
		
		System.out.println(betterString("aBcd", "B", bigLetters));
	}

}
