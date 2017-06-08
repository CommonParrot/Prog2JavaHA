package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.controller.Main;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState.GamePhase;

/**
 * An artificial player controlled by the computer.
 * 
 * @author prog2-team
 *
 */
public class ArtificialPlayer extends Player implements GameStateListener, Runnable {

	/** GameState */
	GameState state = Main.state;
	/** GameLogic */
	GameLogic logic = Main.inputsLocalPlayer.gameLogic;
	/** Name of the active player */
	private String activePlayerName = "";
	/** current game phase */
	private GamePhase gamePhase = GamePhase.Idle;

	/**
	 * Constructor for the artificial player.
	 * 
	 * @param name player name
	 * @param color player color
	 */
	public ArtificialPlayer(String name, PieceColor color) {
		super(name, color);
		state.addGameStateListener(this);
	}

	/**
	 * When called the artificial player tries to perform a turn.
	 */
	public void performTurn() {
		
		if (activePlayerName == this.getName() && gamePhase == GamePhase.Select) {
			MoveTurns moveTurns = new MoveTurns(this);
			JumpTurns jumpTurns = new JumpTurns(this);
//			Turn turnList = moveTurns.getSimpleMoveTurn();
// 			Turn turnList = moveTurns.getBestMoveTurn();
			Turn turnList = jumpTurns.getBestJumpTurn();
			System.out.println(turnList.getScore());
			System.out.println(turnList.getGoal());
			selectPiece(turnList.getPiece());
			
			for(Piece pos : turnList.getJumplist()){
				movePiece(pos.position);
			}
			
			logic.stillActive = false;
			startNextPlayer();
		}
		
	}
	
	/**
	 * If the next player is an artificial player it is started to
	 * perform its turn.
	 */
	private void startNextPlayer() {
		if (state.getActivePlayer() instanceof ArtificialPlayer)
			logic.fieldClicked(new Position(0, 0));
	}

	/**
	 * Chooses and returns a random element from the given collection.
	 * @param coll collection to pick up an element
	 * @return randomly chosen element from the given collection
	 */
	static <T> T getRandomElement(Collection<T> coll) {
		if (coll.isEmpty()) return null;
		final Random r = new Random();
		LinkedList<T> list = new LinkedList<T>(coll);
		return list.get(r.nextInt(list.size()));
	}

	/**
	 * Method for selecting a piece during the artificial player's
	 * turn.
	 */
	private void selectPiece(Piece p) {
		executeClick(new Position(p));
	}
	
	/**
	 * Method for selecting a piece during the artificial player's
	 * turn.
	 * @return the position of the selected piece
	 */
	private void selectPiece(Position p) {
		executeClick(new Position(p));
	}

	/**
	 * Method for moving the given piece. A move can be only a move to an
	 * adjacent position or a chain of consecutive jumps.
	 * 
	 * @param piece piece that should perform a move.
	 */
	private void movePiece(Position pos) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executeClick(pos);
	}
	
	/**
	 * Performs a click at the given position.
	 * @param p Position that should be clicked
	 */
	private void executeClick(Position p) {
		logic.executeFieldClicked(p);
	}


	/**
	 * Calculates a percentage of done progress if moving a piece.
	 * 
	 * @param fromP initial Position of the piece
	 * @param toP Position after moving the piece
	 * @return
	 */
	float getTurnBenefit(Position fromP, Position toP) {
		Position destPos = this.getDestinationCorner().getFarthermostDestinationPosition();
		float distBefore = Board.getDistance(fromP, destPos);
		float distAfter = Board.getDistance(toP, destPos);
		return distBefore - distAfter;
	}
	
	@Override
	protected ArtificialPlayer clone() throws CloneNotSupportedException {
		ArtificialPlayer clone = new ArtificialPlayer(this.getName(), this.getColor());
		for (Piece piece : this.getPieces()) {
			Piece pieceClone = new Piece(this.getColor());
			pieceClone.position = piece.position;
			clone.addPiece(pieceClone);
		}
		return clone;
	}

	@Override
	public void gamePhaseTriggered(GameState gameState) {
		gamePhase = gameState.getGamePhase();
	}

	@Override
	public void playerTurnFinished(Player player) {
	}

	@Override
	public void activePlayerChanged(Player activePlayer) {
		if (activePlayer == null)
			activePlayerName = "";
		else
			activePlayerName = activePlayer.getName();
	}

	@Override
	public void gameStarted() {
	}

	@Override
	public void configurationChanged(GameState gameState) {
	}

	@Override
	public void gameOver(Player winner) {
	}

	@Override
	public void fieldChanged(GameState gameState) {
	}

	@Override
	public void run() {
		performTurn();
	}

}
