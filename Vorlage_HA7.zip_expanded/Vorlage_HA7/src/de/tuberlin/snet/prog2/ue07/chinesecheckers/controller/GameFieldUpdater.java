package de.tuberlin.snet.prog2.ue07.chinesecheckers.controller;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameStateListener;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Piece;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Player;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Position;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.GameField;

public class GameFieldUpdater implements GameStateListener {
	
	final private GameField gameField;
	final private GameState gameState;

	public GameFieldUpdater(GameField gameField, GameState gameState) {
		this.gameField = gameField;
		this.gameState = gameState;
	}

	@Override
	public void gamePhaseTriggered(GameState gameState) {
		updatefield();
	}

	@Override
	public void gameStarted() {}

	@Override
	public void configurationChanged(GameState gameState) {}

	@Override
	public void gameOver(Player winner) {}

	@Override
	public void activePlayerChanged(Player activePlayer) {}

	@Override
	public void playerTurnFinished(Player player) {}
	

	private void updatefield() {
		gameField.clearPieces();
		
		for (Piece p : gameState.getPieces())
			gameField.setPiece(p);
		
		
		Piece selection = gameState.getSelectedPiece();
		if (selection != null) {
			Position lastJumpPosition =  gameState.getLastPosition();
			gameField.setSelection(selection, lastJumpPosition);
		}
		
		gameField.repaint();
	}

	@Override
	public void fieldChanged(GameState gameState) {
		updatefield();
	}
}
