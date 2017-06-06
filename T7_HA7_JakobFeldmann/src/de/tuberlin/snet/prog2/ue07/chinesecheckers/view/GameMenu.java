package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.controller.Main;

public class GameMenu extends JMenuBar {
	
	/**
	 * Menu item for restarting the game.
	 */
	private JMenuItem menuItemR;
	
	public GameMenu() {
		createMenuBar();
		menuItemR.setEnabled(false);
	}

	private void createMenuBar() {
		JMenu menuGame = new JMenu("Game");
		this.add(menuGame);
//		JMenu start = new JMenu("Start");
//		start.setMnemonic(KeyEvent.VK_S);
//		menuGame.add(start);
		menuGame.add(getRestartItem());
		
		menuGame.add(getStartItem());
	}

	private JMenuItem getStartItem() {
		JMenuItem menuStart = new JMenuItem("Start");
		menuStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.dialogStart.showDialog();
			}
		});
		return menuStart;
	}
	
	/**
	 * Enables/disables the 'reset' menu item.
	 * @param resetEnabled whether the menu item shall be enabled
	 */
	public void setResetEnabled(boolean resetEnabled) {
		menuItemR.setEnabled(resetEnabled);
	}
	
	/**
	 * Creates and returns the 'restart' menu item.
	 * @return menu item which, when clicked, restarts the game
	 */
	JMenuItem getRestartItem() {
		menuItemR = new JMenuItem("Neustart");
		menuItemR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.restart();
			}
		});
		menuItemR.setMnemonic(KeyEvent.VK_N);
		
		return menuItemR;
	}

}
