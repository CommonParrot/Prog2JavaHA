package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState.GamePhase;


public interface GameActionListener {

	/**
	 * The next player was activated.
	 */
	public void activateNextPlayer();

	/**
	 * The game phase got changed.
	 * 
	 * @param select New game phase
	 */
	public void setGamePhase(GamePhase select);

	/**
	 * The selected piece got set.
	 * 
	 * @param p Piece that got set.
	 */
	public void setSelectedPiece(Piece k);

	/**
	 * Piece 'p' was moved to field (x, y)
	 * 
	 * @param k The piece that was moved.
	 * @param position position of field
	 */
	public void movePiece(Piece k, Position position);

	/**
	 * The game got over.
	 * 
	 * @param winner Winner of the game.
	 */
	public void setGameOver(Player winner);

	/**
	 * Piece 'p' should be added to the list of jump obstacles.
	 * 
	 * @param k
	 */
	public void addJumpObstacle(Piece k);

	/**
	 * The game got started.
	 * 
	 * @param gameState Game state with new game configuration to be started
	 */
	public void startGame(GameState gameState);

}
