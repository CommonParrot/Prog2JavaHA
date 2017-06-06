package de.tuberlin.snet.prog2.ue07.chinesecheckers.controller;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameStateListener;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Player;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.GameStatusBar;

public class GameStatusBarUpdater implements GameStateListener {
	
	final private GameStatusBar statusBar;

	public GameStatusBarUpdater(GameStatusBar statusBar) {
		this.statusBar = statusBar;
	}

	@Override
	public void gamePhaseTriggered(GameState gameState) {
		String playerName = "";
		if (gameState.getActivePlayer() != null)
			playerName = gameState.getActivePlayer().getName();
		
		String message = playerName + ": ";
		switch (gameState.getGamePhase()) {
		case Idle:
			break;
		case Select:
			message += "waehle eine Figur!";
			break;
		case Move:
			message += "waehle eine Figur zum Drueberspringen oder einen endgueltigen Zielort!";
			break;
		}
		
		
		statusBar.setMessage(message);
	}

	@Override
	public void gameStarted() {}

	@Override
	public void configurationChanged(GameState gameState) {}

	@Override
	public void gameOver(Player winner) {
		statusBar.setMessage(winner.getName() + " gewinnt!");
	}

	@Override
	public void activePlayerChanged(Player activePlayer) {}

	@Override
	public void playerTurnFinished(Player player) {}
	
	@Override
	public void fieldChanged(GameState gameState) {}

}
