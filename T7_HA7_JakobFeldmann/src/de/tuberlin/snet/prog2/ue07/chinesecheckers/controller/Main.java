package de.tuberlin.snet.prog2.ue07.chinesecheckers.controller;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.DialogStart;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.GameFrame;

public class Main {
	/**
	 * The local instantiation of the GameState (for local game).
	 */
	public static final GameState state = new GameState();
	/**
	 * The main frame in which the game is displayed.
	 */
	public static final GameFrame window = new GameFrame();
	/**
	 * Instance of the start dialog for the local game mode.
	 */
	public static final DialogStart dialogStart = new DialogStart();
	/**
	 * Instance of the local users "interface" to communicate with the game model. 
	 */
	public static final InputsLocalPlayer inputsLocalPlayer = new InputsLocalPlayer(state);

	
	public static void main(String[] args) {
		//Register all game state listeners
//		state.addGameStateListener(new AudioEvents());
		state.addGameStateListener(new GameMenuUpdater(window.menuBar));
		state.addGameStateListener(new GameStatusUpdater(window.gameView.status));
		state.addGameStateListener(new GameStatusBarUpdater(window.statusBar));
		state.addGameStateListener(new GameFieldUpdater(window.gameView.field, state));
//		state.addGameStateListener(showRecommendation);
		
		//Register listener for user interface callback
		window.gameView.field.addFieldListener(inputsLocalPlayer);
		
//		ServerMain.initStatic();
	}
	
	/**
	 * Restarts the game. Always switches to local game!
	 */
	public static void restart() {
		state.restartGame();
	}
	
	public static void startGame(GameState gameState) {
		state.set(gameState);
		state.startGame();
	}
	
	/**
	 * A game is continued with a configuration setting.
	 * 
	 * @param gameState State in which the game should be continued.
	 */
	public static void continueGame(GameState gameState) {
		state.set(gameState);
		state.continueGame();	
	}

}
