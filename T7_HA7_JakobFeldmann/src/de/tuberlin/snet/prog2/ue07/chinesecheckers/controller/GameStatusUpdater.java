package de.tuberlin.snet.prog2.ue07.chinesecheckers.controller;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameStateListener;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Player;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.view.GameStatus;

/**
 * This class updates the {@link GameStatus} autonomously 
 * as  the correct listener methods are called.
 *
 */
public class GameStatusUpdater implements GameStateListener {
	
	final private GameStatus status;
	
	/**
	 * Creates a new {@link GameStatusUpdater} instance.
	 * @param status the {@link GameStatus} to update
	 */
	public GameStatusUpdater(GameStatus status) {
		this.status = status;
	}

	@Override
	public void gamePhaseTriggered(GameState gameState) {
		status.setGamePhase(gameState.getGamePhase().name());
		
		Player player = gameState.getActivePlayer();
		if (player == null) return;
		
		for (Player p : gameState.getPlayers()) {
			String pString = GameState.getProgress(p) +"%";
			status.setPlayerProgress(p.getName(), pString);
		}
	}

	@Override
	public void configurationChanged(GameState gameState) {
		status.setGamePhase(gameState.getGamePhase().name());
		String name = "-";
		if (gameState.getStartPlayer() != null)
			name = gameState.getStartPlayer().getName();
		status.setStartPlayer(name);
		status.setPlayers(gameState.getPlayers());
	}

	@Override
	public void activePlayerChanged(Player activePlayer) {
		String activePlayerString = "-";
		if (activePlayer != null)
			activePlayerString = activePlayer.getName();
		status.setActivePlayer(activePlayerString);
	}


	@Override
	public void fieldChanged(GameState gameState) {}
	
	@Override
	public void gameOver(Player winner) {}

	@Override
	public void gameStarted() {}

	@Override
	public void playerTurnFinished(Player player) {}


}
