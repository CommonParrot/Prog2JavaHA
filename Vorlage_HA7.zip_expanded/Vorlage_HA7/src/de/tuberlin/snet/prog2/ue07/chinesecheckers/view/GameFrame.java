package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The main window for the game
 */
public class GameFrame extends JFrame {
	
	/**
	 * Menu bar at the top
	 */
	public final GameMenu menuBar = new GameMenu();
	/**
	 * Status bar at the bottom
	 */
	public final GameStatusBar statusBar = new GameStatusBar();
	/**
	 * Central area for the game view
	 */
	public final GameView gameView = new GameView();
	
	/**
	 * Creates a new instance
	 */
	public GameFrame() {
		createFrame();
	}

	private void createFrame() {
		setTitle("Chinese Checkers");
		setJMenuBar(menuBar);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameView, BorderLayout.CENTER);		
		contentPane.add(statusBar, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(new Dimension(700,600));
	}

}
