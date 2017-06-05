package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * Shows the current game status
 */
public class GameStatusBar extends JLabel {

	/**
	 * Creates a new GameStatusBar
	 */
	public GameStatusBar() {

		setHorizontalAlignment(JLabel.CENTER);
		setBorder( BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setText("Ready to start.");
	}
	
	/**
	 * Shows the specified message
	 * @param text message to show
	 */
	public void setMessage(String text) {
		setText(text);
	}
}
