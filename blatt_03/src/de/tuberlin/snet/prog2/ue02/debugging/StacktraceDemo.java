package de.tuberlin.snet.prog2.ue02.debugging;

public class StacktraceDemo {

	public static void main(String[] args) {
		printOnConsole();
	}

	private static void printOnConsole() {
		printFirstText();
		printSecondText();
	}

	private static void printFirstText() {
		String text = getFirstText();
		System.out.println(text);
	}

	private static String getFirstText() {
		String text = "Erste Ausgabe.";
		return text;
	}
	
	private static void printSecondText() {
		String text = getSecondText();
		System.out.println(text);
	}
	
	private static String getSecondText() {
		String text = "Zweite Ausgabe.";
		return text;
	}

}
