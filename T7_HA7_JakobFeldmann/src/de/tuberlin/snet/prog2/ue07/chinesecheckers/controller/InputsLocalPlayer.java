package de.tuberlin.snet.prog2.ue07.chinesecheckers.controller;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameLogic;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Position;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.FieldListener;


/**
 * This class cares about the communication from Main class to the game logic.
 * 
 * @author prog2-Team
 *
 */
public class InputsLocalPlayer implements FieldListener {
	/**
	 * Game logic which is needed to click a field.
	 */
	public final GameLogic gameLogic;
	
	
	/**
	 * Constructor for InputsLocalPlayer. It sends the current game state to the
	 * game logic, so all have the same state.
	 * 
	 * @param gameState current game state
	 */
	InputsLocalPlayer(GameState gameState) {
		this.gameLogic = new GameLogic(gameState);
	}
	
	/**
	 * Simulates a click on a single field.
	 * 
	 * @param x x coordinate of the field
	 * @param y y coordinate of the field
	 */
	@Override
	public void fieldClicked(Position position) {
		gameLogic.fieldClicked(position);
	}
		

	@Override
	public void fieldExited(Position position) {}

	@Override
	public void fieldEntered(Position position) {}

}
