package de.tuberlin.snet.prog2.ue07.closures;

import java.util.Scanner;
import java.util.function.Function;

public class Closures {

	
	static void greetings(String name){
		/**
		 *  TODO Implementieren des Functional Interface
		 */
		welcomeMessage(greeter);
	}
	
	static void welcomeMessage(Function<String, String> greeter){
		/**
		 *  TODO Aufrufen des Functional Interfaces
		 */
		System.out.println(...);
	}
	
	public static void main (String args[]){
		System.out.println("What's your name?");
		Scanner scanner = new Scanner(System.in);
		String greeting = scanner.nextLine();
		greetings(greeting);
		scanner.close();
	}
	
	
}
