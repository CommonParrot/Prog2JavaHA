package de.tuberlin.snet.prog2.ue07.chinesecheckers.controller;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState.GamePhase;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameStateListener;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Player;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.GameMenu;

public class GameMenuUpdater  implements GameStateListener {
	
	final private GameMenu menu;

	public GameMenuUpdater(GameMenu menuBar) {
		menu = menuBar;
	}

	@Override
	public void gamePhaseTriggered(GameState gameState) {
		boolean resetEnabled = !(gameState.getGamePhase() == GamePhase.Idle);
		menu.setResetEnabled(resetEnabled);
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
	
	@Override
	public void fieldChanged(GameState gameState) {}

}
