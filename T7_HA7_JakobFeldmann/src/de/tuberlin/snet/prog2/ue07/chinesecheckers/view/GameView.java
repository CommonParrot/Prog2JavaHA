package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GameView extends JPanel {
	
	final public GameField field = new GameField();
	final public GameStatus status = new GameStatus();

	public GameView() {
		createGui();
	}


	/**
	 * Creates and adds necessary elements for the game view
	 */
	private void createGui() {		
		JPanel pnlGlueContainer = new JPanel();		
		pnlGlueContainer.setLayout(new BoxLayout(pnlGlueContainer, BoxLayout.PAGE_AXIS));
		pnlGlueContainer.add(Box.createGlue());
		pnlGlueContainer.add(field);
		pnlGlueContainer.add(Box.createGlue());
		
    	setLayout(new BorderLayout());    	
    	add(pnlGlueContainer, BorderLayout.CENTER);
    	add(status, BorderLayout.EAST);

		
    	status.setBackground(Color.black);
    	status.setPreferredSize(new Dimension(250,6000));
	}


}
