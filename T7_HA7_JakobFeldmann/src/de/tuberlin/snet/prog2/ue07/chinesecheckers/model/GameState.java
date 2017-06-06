package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class stores the current game state.
 * 
 * @author prog2-Team
 *
 */
public class GameState {

	public enum GamePhase {
		Idle,
		Select,
		Move
	}
	transient final private List<GameStateListener> stateListeners = new LinkedList<>();
	transient final private List<GameActionListener> actionListeners = new LinkedList<>();
	
	final private Map<String, Player> players = new HashMap<>();
	final private List<String> playerOrder = new LinkedList<>();	
	final private List<Piece> jumpObstacles = new LinkedList<>();
	
	private GamePhase gamePhase;	
	private String startPlayerName;
	private String activePlayerName;
	private Piece selectedPiece;
	



	/**
	 * It's the standard constructor for the GameState. Calls the method GameState#clear().
	 */
	public GameState() {
		clear();
	}

	
	
	/**
	 * Adds a given gameStateListener to the list 'stateListeners'.
	 * 
	 * @param gameStateListener An object implementing the interface 'GameStateListener'
	 */
	public void addGameStateListener(GameStateListener gameStateListener) {
		stateListeners.add(gameStateListener);
	}
	
	/**
	 * Removes a given gameStateListener from the list 'stateListeners'.
	 * 
	 * @param gameStateListener An object implementing the interface 'GameStateListener'
	 */
	public void removeGameStateListener(GameStateListener gameStateListener) {
		stateListeners.remove(gameStateListener);
	}
	
	/**
	 * Add a game action listener to the list of registered game action listeners
	 * 
	 * @param gameActionListener New listener
	 */
	public void addGameActionListener(GameActionListener gameActionListener) {
		actionListeners.add(gameActionListener);
	}
	
	/**
	 * Removes an game action listener from the list of registered game action listeners
	 * 
	 * @param gameActionListener
	 */
	public void removeGameActionListener(GameActionListener gameActionListener) {
		actionListeners.remove(gameActionListener);
	}
	
	
	
	/**
	 * Sets all attributes of the game state to the attributes of the given game state.
	 * 
	 * @param gameState given game state
	 */
	public void set(GameState gameState) {
		clear();
		setGamePhase(gameState.gamePhase);
		setActivePlayerName(gameState.activePlayerName);
		
		this.startPlayerName = gameState.startPlayerName;
		
		for (Player p : gameState.getPlayers()) {
			players.put(p.getName(), p);
		}
	
		for (int i=0; i<gameState.playerOrder.size(); i++)
			this.playerOrder.add(gameState.playerOrder.get(i));

		fireConfigurationChanged();
	}
	
	
	/**
	 * Method to initialise a game state.
	 */
	private void _init() {
		setGamePhase(GamePhase.Idle);
		setActivePlayerName(this.startPlayerName);
		selectedPiece = null;
		jumpObstacles.clear();
		
		for (Player p : players.values()) {
			p.initPieces();
		}

		fireFieldChanged();
		fireConfigurationChanged();
	}
	
	/**
	 * Starts a new game.
	 * Initialising all pieces, sets the active player and game phase.
	 */
	public void startGame() {
		fireStartGame();
		for (Player p : getPlayers()) {
			p.initPieces();
		}
		setActivePlayerName(startPlayerName);
		setGamePhase(GamePhase.Select);
		fireGameStarted();
	}
	
	/**
	 * Continues the game. 
	 */
	public void continueGame() {
		setActivePlayerName(activePlayerName);
		setGamePhase(getGamePhase());
	}

	/**
	 * Restarts a game.
	 */
	public void restartGame() {
		_init();
		startGame();
	}

	/**
	 * Clears all attributes of the game state. 
	 */
	public void clear() {
		_init();
		players.clear();
		playerOrder.clear();
		startPlayerName = null;
		activePlayerName = null;
		selectedPiece = null;
		jumpObstacles.clear();
			
		fireConfigurationChanged();
	}
	


	
	/**
	 * Getter method delivering the player order in a list.
	 * 
	 * @return list of players ordered
	 */
	public Collection<String> getPlayerOrder() {
		return playerOrder;
	}


	/**
	 * Sets the playerOrder by a given list.
	 * 
	 * @param playerOrder list of players ordered
	 */
	public void setPlayerOrder(List<String> playerOrder) {
		this.playerOrder.clear();
		this.playerOrder.addAll(playerOrder);
	}

	
	/**
	 * Sets start player with given name. 
	 * 
	 * @param name name of a player
	 */
	public void setStartPlayerName(String name) {
		this.startPlayerName = name;
		fireConfigurationChanged();
	}
	
	/**
	 * Sets active player with given name. 
	 * 
	 * @param playerName name of a player
	 */
	public void setActivePlayerName(String playerName) {
		firePlayerTurnFinished();
		this.activePlayerName = playerName;
		fireActivePlayerChanged();
	}


	/**
	 * Delivers the start player of the game state.
	 * 
	 * @return start player
	 */
	public Player getStartPlayer() {
		return players.get(startPlayerName);
	}
	
	/**
	 * Delivers the active player of the game state.
	 * 
	 * @return active player
	 */
	public Player getActivePlayer() {
		return players.get(activePlayerName);
	}


	/**
	 * Adds a new player to the game.
	 * Puts also the new player in player order.
	 * 
	 * @param player new player to add
	 */
	public void addPlayer(Player player) {
		players.put(player.getName(), player);
		playerOrder.add(player.getName());
		fireConfigurationChanged();
	}


	/**
	 * Delivers all players participating in this game.
	 * 
	 * @return list of players
	 */
	public Collection<Player> getPlayers() {
		return players.values();
	}


	/**
	 * Delivers the selected piece.
	 * 
	 * @return piece selected by user
	 */
	public Piece getSelectedPiece() {
		return selectedPiece;
	}


	/**
	 * Clears the selected piece.
	 */
	public void clearSelection() {
		selectedPiece = null;
		jumpObstacles.clear();
	}


	/**
	 * Sets the selected piece.
	 * 
	 * @param p piece selected by user
	 */
	public void setSelectedPiece(Piece p) {
		fireSetSelectedPiece(p);
		
		this.selectedPiece = p;				
	}
	

	
	/**
	 * Adds a piece to the list of jump obstacles, which can
	 * be jumped over by the selected piece.
	 * 
	 * @param obstacle piece which can be jumped over
	 */
	public void addJumpObstacle(Piece obstacle) {
		fireAddJumpObstacle(obstacle);
		
		jumpObstacles.add(obstacle);		
		List<Position> positions = getJumpPositions();
		if (positions.size() < 2) return;
	}


	/**
	 * Sets the next player in player order to active player.
	 */
	public void activateNextPlayer() {
		fireActivateNextPlayer();
		
		int index = playerOrder.indexOf(activePlayerName);
		int next = (index +1) % playerOrder.size();
		String activePlayerName = playerOrder.get(next);
		setActivePlayerName(activePlayerName);
		
		fireGamePhaseTriggered();
	}

	
	/**
	 * Moves a piece to field[x][y].
	 * 
	 * @param p piece to move
	 * @param position position of the field
	 */
	public void movePiece(Piece p, Position position) {
		fireMovePiece(p, position);
	
		p.position = position;
		
		clearSelection();
		fireFieldChanged();
	}


	/**
	 * Sets game phase to given turn.
	 * 
	 * @param turn game phase
	 */
	public void setGamePhase(GameState.GamePhase turn) {
		fireSetGamePhase(turn);
		
		this.gamePhase = turn;
		fireGamePhaseTriggered();		
	}


	/**
	 * Sets the game phase to idle again.
	 * 
	 * @param winner player which won
	 */
	public void setGameOver(Player winner) {
		fireSetGameOver(winner);
		
		this.gamePhase = GamePhase.Idle;
		fireGamePhaseTriggered();
		fireGameOver(winner);
	}


	/**
	 * Delivers current game phase.
	 * 
	 * @return current game phase
	 */
	public GameState.GamePhase getGamePhase() {
		return gamePhase;
	}


	/**
	 * Delivers all pieces in the game.
	 * 
	 * @return pieces in current game
	 */
	public Collection<Piece> getPieces() {
		List<Piece> pieces = new LinkedList<>();
		for (Player p : players.values())
			pieces.addAll(p.getPieces());
		return pieces;
	}
	
	/**
	 * Checks if a game state is valid. What means valid?
	 * A valid game state is a state, where:
	 * <li> there is at least one player,
	 * <li> the player order is defined,
	 * <li> the starting player is defined,
	 * <li> the piece count is greater 0,
	 * <li> no two players have either the same name or color,
	 * <li> no player has more pieces than the piece count variable says,
	 * <li> every piece has the color of its player,
	 * <li> the game phase is <i>idle</i> in case there exist no piece,
	 * <li> the game phase is either <i>idle</i>, or (exclusive or) an active player is defined,
	 * <li> no piece is selected when the game phase is <i>select</i>.
	 * 
	 */
	public boolean isValid() {
		boolean isValid = true;
		
		isValid &= startPlayerName != null;
		isValid &= playerOrder.contains(startPlayerName);
		
		isValid &= playerOrder.size() > 0;
		isValid &= playerOrder.size() == players.size();
		
		
		// players and player order are consistent
		for (Player p : players.values())
			isValid &= playerOrder.contains(p.getName());

		// all colors of one player and its pieces are the same
		for (Player p : players.values())
			for (Piece pi : p.getPieces())
				isValid &= p.getColor().equals(pi.color);
			
		// no player has more pieces than allowed
		for (Player p : players.values())
			isValid &= p.getPieces().size() <= Board.Corner.North.locations().size();

		// there are no two player with the same color or name
		for (int i1 = 0; i1 < players.size(); i1++)
			for (int i2 = i1+1; i2 < players.size(); i2++) {
				String pN1 = playerOrder.get(i1);
				String pN2 = playerOrder.get(i2);
				PieceColor c1 = players.get(pN1).getColor();
				PieceColor c2 = players.get(pN2).getColor();
				
				isValid &= !pN1.equals(pN2);
				isValid &= !c1.equals(c2);				
			}
		
		// Either there is an active player, xor the game phase is idle 
		isValid &= activePlayerName != null ^ gamePhase == GamePhase.Idle;
		
		if (getPieces().isEmpty())
			isValid &= gamePhase == GamePhase.Idle;	
		
		if (gamePhase == GamePhase.Select)
			isValid &= selectedPiece == null;
		
		if (gamePhase == GamePhase.Move)
			isValid &= selectedPiece != null;

		return isValid;
	}
	
	/**
	 * Delivers the winner of the game.
	 * If there is no winner, method returns null.
	 * 
	 * @return winner
	 */
	public Player getWinner() {
		
		for (Player p : players.values()) {
			boolean wins = true;
			for (Piece pi : p.getPieces())
				wins &= Board.isCorner(p.getDestinationCorner(), pi.position);
			if (wins==true)
				return p;
		}	
		
		return null;
	}
	
	/**
	 * Tests if the field[x][y] is empty (no piece occupies the field)
	 * 
	 * @param position position of the field
	 * @return true if field[x][y] is empty,
	 * 		   false if field[x][y] is not empty
	 */
	public boolean isEmpty(Position position) {
		return getPiece(position) == null;
	}


	/**
	 * Delivers piece on field[x][y].
	 * 
	 * @param position position of the field
	 * @return piece on field[x][y]
	 */ 
	public Piece getPiece(Position position) {
		for (Piece p : getPieces())
			if (p.hasPosition(position))
				return p;
		return null;
	}


	/**
	 * Delivers a list of positions which defines the track of the jumps.
	 * 
	 * @return list of positions with possible jumps
	 */
	public List<Position> getJumpPositions() {
		return getJumpPositions(selectedPiece, jumpObstacles);
	}


	/**
	 * Delivers a list of positions where pieces on to jump over.
	 * 
	 * @param selectedPiece piece selected by user
	 * @param jumpObstacles pieces to jump over
	 * @return list of positions with possible jumps
	 */
	static public List<Position> getJumpPositions(Piece selectedPiece, List<Piece> jumpObstacles) {
		List<Position> positions = new LinkedList<>();
		if (selectedPiece != null) {
			Position lastPosition = new Position(selectedPiece.position);
			positions.add(lastPosition);
			for (Piece jk : jumpObstacles) {
				Position newPosition = getNextPosition(lastPosition, jk);
				lastPosition = newPosition;
				positions.add(lastPosition);
			}
		}
		return positions;
	}


	/**
	 * Calculates the new position out of selected piece position and position of
	 * the piece to jump over.
	 * 
	 * @param lastPosition position of selected piece
	 * @param obstacleP position of piece to jump over
	 * @return calculated destination position for selected piece
	 */
	static public Position getNextPosition(Position lastPosition, Piece obstacleP) {
		int diffX = obstacleP.position.x - lastPosition.x;
		int diffY = obstacleP.position.y - lastPosition.y;
		Position newPosition = new Position(obstacleP.position.x + diffX, obstacleP.position.y + diffY);
		return newPosition;
	}



	/**
	 * Delivers the destination position of a whole move phase.
	 * 
	 * @return destination position 
	 */
	public Position getLastPosition() {
		return getLastPosition(selectedPiece, jumpObstacles);
	}
	
	/**
	 * Delivers the destination position of a whole move phase.
	 * 
	 * @param selectedPiece piece selected by user
	 * @param jumpObstacles pieces to jump over
	 * @return destination position
	 */
	static public Position getLastPosition(Piece selectedPiece, List<Piece> jumpObstacles) {
		if (selectedPiece == null) return null;
		
		Position lastPosition = null;
		List<Position> jumpPositions = getJumpPositions(selectedPiece, jumpObstacles);
		lastPosition = jumpPositions.get(jumpPositions.size() -1);
		
		return lastPosition;
	}


	/**
	 * Calculates a percentage of done progress for player.
	 * 
	 * @param player player the progress is requested 
	 * @return percentage progress of player
	 */
	public static float getProgress(Player player) {
		if (player == null) return 0;
		
		Collection<Position> positions = player.getPieces().stream().
				map(Position::new).
				collect(Collectors.toList());
		float[] centroid = Board.getCentroid(positions);
		
		float[] startPos = Board.getCentroid(player.getStartCorner().locations());
		float[] destPos = Board.getCentroid(player.getDestinationCorner().locations());
		float maxDist = Board.getDistance(startPos, destPos);
		float dist = Board.getDistance(centroid, destPos);
		return 100-(dist*100/maxDist);
	}


	/**
	 * Delivers all player names of the game.
	 * 
	 * @return list of all player names
	 */
	public Collection<String> getPlayerNames() {
		return players.keySet();
	}
	
	/**
	 * Delivers all player colors of the game.
	 * 
	 * @return list of all player colors
	 */
	public Collection<PieceColor> getPlayerColors() {
		Collection<PieceColor> colors = new LinkedList<PieceColor>();
		for (Player player : players.values())
			colors.add(player.getColor());
		return colors;
	}
	
	
	
	
	/** Fire Events of GameStateListeners */
	
	void fireGamePhaseTriggered() {
		for (GameStateListener tl : stateListeners)
			tl.gamePhaseTriggered(this);
	}
	
	void fireActivePlayerChanged() {
		for (GameStateListener tl : stateListeners)
			tl.activePlayerChanged(getActivePlayer());
	}
	
	void firePlayerTurnFinished() {
		for (GameStateListener tl : stateListeners)
			tl.playerTurnFinished(getActivePlayer());
	}

	void fireGameOver(Player winner) {
		for (GameStateListener tl : stateListeners)
			tl.gameOver(winner);
	}
	
	void fireGameStarted() {
		for (GameStateListener tl : stateListeners)
			tl.gameStarted();
	}
	
	void fireConfigurationChanged() {
		for (GameStateListener tl : stateListeners)
			tl.configurationChanged(this);
	}
	
	void fireFieldChanged() {
		for (GameStateListener tl : stateListeners)
			tl.fieldChanged(this);
	}
	
	

	/** Fire Events of GameActionListeners */
	/**
	 * Triggers the event that the game gets started
	 */
	void fireStartGame() {
		for (GameActionListener tl : actionListeners)
			tl.startGame(this);
	}
	
	/**
	 * Triggers the event that the next player should make his turn
	 */
	void fireActivateNextPlayer() {
		for (GameActionListener tl : actionListeners)
			tl.activateNextPlayer();
	}
	
	/**
	 * Triggers the event that the game phase gets changed
	 */
	void fireSetGamePhase(GamePhase select) {
		for (GameActionListener tl : actionListeners)
			tl.setGamePhase(select);
	}
	
	/**
	 * Triggers the event that a piece gets selected 
	 */
	void fireSetSelectedPiece(Piece p) {
		for (GameActionListener tl : actionListeners)
			tl.setSelectedPiece(p);
	}
	
	/**
	 * Triggers the event that a piece moves
	 */
	void fireMovePiece(Piece p, Position position) {
		for (GameActionListener tl : actionListeners)
			tl.movePiece(p, position);
	}
	
	/**
	 * Triggers the event that the game is over
	 */
	void fireSetGameOver(Player winner) {
		for (GameActionListener tl : actionListeners)
			tl.setGameOver(winner);
	}
	
	/**
	 * Triggers the event that a piece should be added to the list of jump obstacles
	 */
	void fireAddJumpObstacle(Piece k) {
		for (GameActionListener tl : actionListeners)
			tl.addJumpObstacle(k);
	}

}

