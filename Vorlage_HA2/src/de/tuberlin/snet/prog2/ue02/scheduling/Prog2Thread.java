package de.tuberlin.snet.prog2.ue02.scheduling;

/**
 * Can be used as a "long working thread". Calculates Pi up to the given
 * derivative.
 * 
 * Created by prog2-team
 */
public class Prog2Thread extends Thread {
	char letter;

	/**
	 * Creates a thread printing "PR*G2!" with the given letter as "*".
	 * 
	 * @param letter
	 */
	public Prog2Thread(char letter) {
		this.letter = letter;
	}

	/**
	 * Prints "PR*G2!" to the console. "*" is a placeholder for a predefined
	 * letter.
	 */
	@Override
	public void run() {
		try {
			sleep(500);
			System.out.print("P");
			sleep(500);
			System.out.print("R");
			sleep(500);
			System.out.print(letter);
			sleep(500);
			System.out.print("G");
			sleep(500);
			System.out.print("2");
			sleep(500);
			System.out.print("!\n");
		} catch (InterruptedException e) {

		}
	}
}
