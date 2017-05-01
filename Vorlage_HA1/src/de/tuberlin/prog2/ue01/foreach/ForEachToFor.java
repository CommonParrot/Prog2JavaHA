package de.tuberlin.prog2.ue01.foreach;

import java.util.Iterator;

import java.util.Vector;

//does this change show up anywhere?

public class ForEachToFor {
	
	public static void forLoop1A(String [] names){
		
		for(String i: names){
			System.out.println("1A: " + i);
		}
		
	}
	
	public static void forLoop2A(Vector<String> names){
		
		for(String it: names){
			
			System.out.println("2A: " + it);
			
		}
		
	}
	
	private static void forLoop3B(int n){
		
		int[] arrNumbers = new int[n];
		int count = 0;
		for(int x: arrNumbers){
			
			arrNumbers[count] = count + 4;
			++count;
			
		}
		
		for(int it = 0; it <arrNumbers.length; it++){
			
			System.out.println("3B: " + arrNumbers[it]);
			
		}
		
	}
	
	
}
