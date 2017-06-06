package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState.GamePhase;

/**
 * This class cares about the whole game logic like piece moving/jumping.
 * 
 * @author prog2-Team
 *
 */
public class GameLogic {

	final GameState gameState;
	public boolean stillActive = false;

	public GameLogic(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * Describes the event that a player clicked on the field [x][y].
	 * 
	 * @param position position of the clicked field
	 */
	public void fieldClicked(Position position) {
		if (!stillActive) {
			if (!(gameState.getActivePlayer() instanceof ArtificialPlayer))
				executeFieldClicked(position);
			if (gameState.getActivePlayer() instanceof ArtificialPlayer) {
				stillActive = true;
				new Thread((ArtificialPlayer) gameState.getActivePlayer()).start();
			}
		}
	}
	
	/**
	 * Describes the event that the user clicked on the field [x][y].
	 * 
	 * @param x x coordinate of the clicked field
	 * @param y y coordinate of the clicked field
	 */
	protected void executeFieldClicked(Position position) {
		switch (gameState.getGamePhase()) {
		case Select:
			selectAt(position);
			break;
		case Move:
			if (gameState.getSelectedPiece() != null) {
				if (gameState.getSelectedPiece().hasPosition(position)) {
					gameState.clearSelection();
					gameState.setGamePhase(GamePhase.Select);
					break;
				}
			}
			if (gameState.getSelectedPiece() != null)
				tryMovetoAdjacentPlace(position);
			if (gameState.getSelectedPiece() != null)
				tryJumpOverObstacle(position);
			if (gameState.getSelectedPiece() != null)
				tryFinishMove(position);
			break;
		default:
			break;
		}
	}

	/**
	 * Sets a piece placed at field[x][y] as selected piece.
	 * 
	 * @param position position of the field
	 */
	private void selectAt(Position position) {
		for (Piece p : gameState.getActivePlayer().getPieces())
			if (p.hasPosition(position)) {
				gameState.setSelectedPiece(p);
				gameState.setGamePhase(GamePhase.Move);
			}
	}

	/**
	 * Moves a piece to the given destination field. Also tests if the move can
	 * be done.
	 * 
	 * @param position position of the destination field
	 */
	private void tryMovetoAdjacentPlace(Position position) {
		Piece p = gameState.getSelectedPiece();
		Position pos = new Position(p);
		boolean validMove = Board.canMoveTo(gameState, pos, position);
		if (validMove) {
			move(position, p);
		}
	}

	/**
	 * Performs a jump over another piece. Also tests if the jump is possible.
	 * 
	 * @param position position of the field with a piece
	 */
	private void tryJumpOverObstacle(Position position) {
		Piece p = gameState.getPiece(position);
		if (p == null)
			return;

		Position lastPosition = gameState.getLastPosition();
		if (!Board.canJumpOver(gameState, lastPosition, p))
			return;

		gameState.addJumpObstacle(p);
		gameState.setGamePhase(GamePhase.Move);
	}

	/**
	 * Finishes the move phase for a player and activate the next one. Also
	 * tests if the field clicked is occupied by the selected piece. Uses
	 * move(Position, Piece) for that functionality.
	 * 
	 * @param position position of the field
	 */
	private void tryFinishMove(Position position) {
		Position lastPosition = gameState.getLastPosition();

		if (lastPosition == null)
			return;
		if (lastPosition.hasPosition(position)) {
			Piece p = gameState.getSelectedPiece();
			move(position, p);
		}
	}

	/**
	 * Moves a piece to field[x][y]. Tests if there is a winner, activates next
	 * player and sets game phase. To finish move phase, x,y coordinates just
	 * have to be the same as the coordinates of the field the selected piece
	 * stands on.
	 * 
	 * @param x x coordinate of the field
	 * @param y y coordinate of the field
	 * @param p piece to move
	 */
	private void move(Position position, Piece p) {
		gameState.movePiece(p, position);
		Player winner = gameState.getWinner();
		if (winner != null) {
			gameState.setGameOver(winner);
		} else {
			gameState.activateNextPlayer();
			gameState.setGamePhase(GamePhase.Select);
		}
	}

}
