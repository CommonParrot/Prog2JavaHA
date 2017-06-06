package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;


/**
 * Interface for GameStateListeners. 
 * You can find the description of needed methods on Blatt 02 Hausaufgabe 3.2.
 * 
 * @author prog2-Team
 *
 */
public interface GameStateListener {
	
	public void gamePhaseTriggered(GameState gameState);
	
	public void playerTurnFinished(Player player);
	
	public void activePlayerChanged(Player activePlayer);
	
	public void gameStarted();
	
	public void configurationChanged(GameState gameState);
	
	public void gameOver(Player winner);

	public void fieldChanged(GameState gameState);

}