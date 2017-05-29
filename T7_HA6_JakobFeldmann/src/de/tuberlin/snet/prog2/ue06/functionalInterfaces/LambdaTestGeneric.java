package de.tuberlin.snet.prog2.ue06.functionalInterfaces;

import java.lang.reflect.Array;

public class LambdaTestGeneric<T> {

	public T betterInstance(T a, T b, CompInstance<T> c) {
		if (c.instanceCompare(a, b) == true) {
			return a;
		} else {
			return b;
		}
	}

	public static void main(String[] args) {

		
		
		LambdaTestGeneric<String> compStringErsatz = new LambdaTestGeneric<>();

		CompInstance<String> first = (String a, String b) -> true;

		System.out.println(compStringErsatz.betterInstance("a", "b", first));

		
		
		CompInstance<String> longer = (String a, String b) -> a.length() > b.length();

		System.out.println(compStringErsatz.betterInstance("abcd", "bc", longer));

		
		
		CompInstance<String> bigLetters = (String a, String b) -> {

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

		System.out.println(compStringErsatz.betterInstance("aBcd", "B", bigLetters));

		
		
		LambdaTestGeneric<Integer> intComp = new LambdaTestGeneric<>();

		CompInstance<Integer> zahlVergl = (Integer a, Integer b) -> a < b;

		System.out.println(intComp.betterInstance(4, 5, zahlVergl));
		
		
		
		LambdaTestGeneric<Integer[]> arrComp = new LambdaTestGeneric<>();

		CompInstance<Integer[]> arrVergl = (Integer[] a, Integer[] b) -> Array.getLength(a) < Array.getLength(b);
		
		Integer[] b = {1,0,9,7,4,6,235};
		
		Integer[] a = {1,23,14};
		
		for(Integer index : arrComp.betterInstance(a, b, arrVergl)){
			System.out.print(index);
		}

	}
}
