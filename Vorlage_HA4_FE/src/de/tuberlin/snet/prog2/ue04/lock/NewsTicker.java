package de.tuberlin.snet.prog2.ue04.lock;

/**
 * The NewsTicker is the platform for NewsTeam to post to.
 *
 */
public class NewsTicker {

	/**
	 * Displays a given piece of news on the screen.
	 * 
	 * @param message
	 *            the piece of new to display
	 */
	public void displayMessage(String message) {
		System.out.println("\n\n\n\n\n\n\n\n\n");
		char[] m = message.toCharArray();
		for (char c : m) {
			System.out.print(c);
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
