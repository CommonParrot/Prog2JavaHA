package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Defines properties of the Chinese Checkers game board. 
 */
public class Board {

	static final public int dimensionX = 19;
	static final public int dimensionY = 25;

	static final public Collection<Position> allPossibleLocations = new HashSet<>();
	static final public Collection<Position> allHorizontalLines = new HashSet<>();
	static final public Collection<Position> allNorthEastLines = new HashSet<>();
	static final public Collection<Position> allSouthEastLines = new HashSet<>();
	static final public Collection<Position> northConerLocations = new HashSet<>();
	static final public Collection<Position> northEastConerLocations = new HashSet<>();
	static final public Collection<Position> northWestConerLocations = new HashSet<>();
	static final public Collection<Position> southConerLocations = new HashSet<>();
	static final public Collection<Position> southEastConerLocations = new HashSet<>();
	static final public Collection<Position> southWestConerLocations = new HashSet<>();
	
	
	static {
		Collection<Position> allPossibleLocations_ = new LinkedList<>();
		Collection<Position> allHorizontalLines_ = new LinkedList<>();
		Collection<Position> allNorthEastLines_ = new LinkedList<>();
		Collection<Position> allSouthEastLines_ = new LinkedList<>();
		Collection<Position> northConerLocations_ = new LinkedList<>();
		Collection<Position> northEastConerLocations_ = new LinkedList<>();
		Collection<Position> northWestConerLocations_ = new LinkedList<>();
		Collection<Position> southConerLocations_ = new LinkedList<>();
		Collection<Position> southEastConerLocations_ = new LinkedList<>();
		Collection<Position> southWestConerLocations_ = new LinkedList<>();
		// (x,y)=(0,0) is in the centre position, then shift to upper left
	
		// add positions that can be placed by a piece to allPossibleLocations
		for (int y=0; y<dimensionY/2+1; y = y+2)
			for (int x=(y/2)%2; x<dimensionX/2+1; x = x+2) {
				if (x*2+y > 12) {
					if (y > 6)
						continue;
					if (2*x-y > 12)
						continue;
				}
				final int[] factors = {-1,1};
				for (int xFac : factors) 
					for (int yFac : factors) {
						allPossibleLocations_.add(new Position( xFac*x, yFac*y));
						if (x>0)
							allHorizontalLines_.add(new Position(xFac*(x-1), yFac*y));
					}
				// ohne unteren und rechten Rand:
				if (x*2-y < 12 && y>0) {
					allSouthEastLines_.add(new Position(x, y-1));
					allNorthEastLines_.add(new Position(x, -(y-1)));
					allNorthEastLines_.add(new Position(-(x+1), y-1));
					allSouthEastLines_.add(new Position(-(x+1), -(y-1)));
				}
				// ohne unteren und linken Rand:
				if (x>0 && y>0) {
					allNorthEastLines_.add(new Position(x-1, y-1));
					allSouthEastLines_.add(new Position(x-1, -(y-1)));
					allSouthEastLines_.add(new Position(-x, y-1));
					allNorthEastLines_.add(new Position(-x, -(y-1)));
				}
			}
		
		// add positions from allPossibleLocations to the appropriate corner
		for (Position pos : allPossibleLocations_) {
			int x = pos.x;
			int y = pos.y;
			// NE
			if (2*x+y>8 && y<7 && 2*x-y<13) {
				northEastConerLocations_.add(new Position(x, y));
				southEastConerLocations_.add(new Position(x, -y));
				northWestConerLocations_.add(new Position(-x, y));
				southWestConerLocations_.add(new Position(-x, -y));
			}
			if (y>5 && x>-4 && x<4) {
				northConerLocations_.add(new Position(x, y));
				southConerLocations_.add(new Position(x, -y));
			}
		}
		
		allPossibleLocations.addAll(shiftPositions(allPossibleLocations_));
		allHorizontalLines.addAll(shiftPositions(allHorizontalLines_));
		allNorthEastLines.addAll(shiftPositions(allNorthEastLines_));
		allSouthEastLines.addAll(shiftPositions(allSouthEastLines_));
		
		northConerLocations.addAll(shiftPositions(northConerLocations_));
		northEastConerLocations.addAll(shiftPositions(northEastConerLocations_));
		northWestConerLocations.addAll(shiftPositions(northWestConerLocations_));
		southConerLocations.addAll(shiftPositions(southConerLocations_));
		southEastConerLocations.addAll(shiftPositions(southEastConerLocations_));
		southWestConerLocations.addAll(shiftPositions(southWestConerLocations_));
	}
	
	/**
	 * Shifts the positions to the right coordinates, for preparation only.
	 * @param col collection of Positions to be shiftet
	 */
	private static Collection<Position> shiftPositions(Collection<Position> col) {
		Collection<Position> shiftedPositions = new LinkedList<>();
		for (Position pos : col) {
			shiftedPositions.add(new Position(pos.x + dimensionX/2, - pos.y + dimensionY/2));
		}
		return shiftedPositions;
	}
	
	/**
	 * This enum class gives the collections of the six corners names.
	 * @author prog2-team
	 *
	 */
	public enum Corner {
		North (northConerLocations),
		NorthEast (northEastConerLocations),
		SouthEast (southEastConerLocations),
		South (southConerLocations),
		SouthWest (southWestConerLocations),
		NorthWest (northWestConerLocations);
		
		private final Collection<Position> locations;
		
		Corner (Collection<Position> locations) {
			this.locations = locations;
		}
		
		/**
		 * Returns the positions of the corner.
		 * @return collection of positions
		 */
		public Collection<Position> locations() {
			return locations;
		}
		
		/**
		 * Returns the outermost position of the corner.
		 * @return position
		 */
		public Position getFarthermostDestinationPosition() {
			for (Position pos : locations) {
				if (pos.x == 0 || pos.x == dimensionX-1)
					return pos;
				if (pos.y == 0 || pos.y == dimensionY-1)
					return pos;
			}
			return null;
		}
		
		/**
		 * Returns the middle position of the corner.
		 * @return position
		 */
		public Position getMiddlePosition() {
			float[] centroid = Board.getCentroid(locations);
			return new Position((int)centroid[0], (int)centroid[1]);
		}
	}
	
	/**
	 * Tests if a given field, defined by x/y coordinate of the field, is on the given corner 
	 * 
	 * @param corner corner to be checked
	 * @param x x coordinate of the field
	 * @param y y coordinate of the field
	 * @return true if field is in this corner, 
	 * 		   else false
	 */
	static public boolean isCorner(Corner corner, Position position) {
		return corner.locations.contains(position);
	}
	
	/**
	 * Tests if a given position is on the game field.
	 * 
	 * @param nextPos given position
	 * @return true if position is on the game field, 
	 * 		   false if position is outside the game field
	 */
	public static boolean isOnBoard(Position nextPos) {
		return allPossibleLocations.contains(nextPos);
	}
	
	/**
	 * Tests if a field is between two other fields.
	 * 
	 * @param p1 origin
	 * @param p2 destination
	 * @param pX position between p1 and p2? 
	 * @return true if position px is between p1 and p2,
	 * 		   false if position px is not between p1 and p2
	 */
	static boolean isInbetween(Position p1, Position p2, Position pX) {
		
		float dx1 = p1.x - p2.x;
		float dy1 = p1.y - p2.y;
		boolean diag1 = (Math.abs(dx1) == Math.abs(dy1));
		
		float dx2 = pX.x - p2.x;
		float dy2 = pX.y - p2.y;
		boolean diag2 = (Math.abs(dx2) == Math.abs(dy2));
				
		
		boolean inBetween = true;
		// entweder beide sind diagonal, oder keine
		inBetween &= (diag1 == diag2);
		
		// liegt pX zwischen p1 und p2?
		inBetween &= Math.signum(dx1) == Math.signum(dx2);
		inBetween &= Math.signum(dy1) == Math.signum(dy2);
		inBetween &= 0 <= Math.abs(dx2) && Math.abs(dx2) <= Math.abs(dx1);
		inBetween &= 0 <= Math.abs(dy2) && Math.abs(dy2) <= Math.abs(dy1);
		inBetween &= !pX.equals(p1) && !pX.equals(p2);
		
		return inBetween;		
	}

	/**
	 * Tests if a piece can jump over another piece.
	 * The other piece must be adjacent.
	 * A piece can't jump if destination position is 
	 * not in the game field, or the destination position
	 * is already occupied.
	 * 
	 * @param gameState current game state
	 * @param fromPos origin
	 * @param overP piece to jump over
	 * @return true if the piece can jump over another piece,
	 * 		   false if the piece can't jump over another piece
	 */
	public static boolean canJumpOver(GameState gameState, Position fromPos, Piece overP) {
		
		Position nextPos = GameState.getNextPosition(fromPos, overP);
		
		boolean validJump = true;
		validJump &= isNeighbour(fromPos, new Position(overP));
		validJump &= isNeighbour(new Position(overP), nextPos);
		validJump &= Board.isOnBoard(nextPos);
		validJump &= gameState.isEmpty(nextPos);
		return validJump;
	}

	/**
	 * Tests if a piece can move to another position.
	 * To move to destination position, the position has to be
	 * on the board and not occupied by another piece.
	 * 
	 * @param gameState current game state
	 * @param fromPos origin
	 * @param toPos destination
	 * @return true if piece can move to position,
	 * 		   false if piece can't move to position
	 */
	public static boolean canMoveTo(GameState gameState, Position fromPos, Position toPos) {
		boolean validMove = true;
		validMove &= isNeighbour(fromPos, toPos);
		validMove &= Board.isOnBoard(toPos);
		validMove &= gameState.isEmpty(toPos);
		return validMove;
	}
	
	/**
	 * Tests if the given Positions are adjacent.
	 * @param fromPos first Position
	 * @param toPos second Position
	 * @return true, if Positions are adjacent, else false
	 */
	public static boolean isNeighbour(Position fromPos, Position toPos) {
		return getAllNeighbourPositions(fromPos).contains(toPos);
	}
	
	/**
	 * Returns all neighbour Positions. The Positions have to be on the board. 
	 * @param fromPos central Position
	 * @return neighbours of the given Position
	 */
	public static Collection<Position> getAllNeighbourPositions(Position fromPos) {
		Collection<Position> neighbours = new LinkedList<>();
		neighbours.add(new Position(fromPos.x + 2, fromPos.y));
		neighbours.add(new Position(fromPos.x - 2, fromPos.y));
		neighbours.add(new Position(fromPos.x + 1, fromPos.y - 2));
		neighbours.add(new Position(fromPos.x + 1, fromPos.y + 2));
		neighbours.add(new Position(fromPos.x - 1, fromPos.y - 2));
		neighbours.add(new Position(fromPos.x - 1, fromPos.y + 2));
		neighbours.removeIf(e -> !Board.isOnBoard(e));
		return neighbours;
	}
	
	/**
	 * Returns all possible move turns from the given Position.
	 * A move is possible if a Position is adjacent, on the board
	 * and not occupied by a piece.
	 * @param gameState current GameStat
	 * @param fromPos Position to start the move
	 * @return Collection of the possible Positions to move
	 */
	public static Collection<Position> getAllMoveTurns(GameState gameState, Position fromPos) {
		Collection<Position> neighbours = getAllNeighbourPositions(fromPos);
		neighbours.removeIf(e -> !gameState.isEmpty(e));
		return neighbours;
	}
	
	
	/**
	 * Returns all adjacent pieces of the given Position.
	 * @param gameState current GameState
	 * @param fromPos central Position
	 * @return Collection of the adjacent pieces
	 */
	public static Collection<Piece> getAllNeighbouringPieces(GameState gameState, Position fromPos) {
		Collection<Position> neighbours = getAllNeighbourPositions(fromPos);
		neighbours.removeIf(e -> gameState.isEmpty(e));
		return neighbours.stream().map(p -> gameState.getPiece(p)).collect(Collectors.toList());
	}
	
	/**
	 * Returns all pieces that can be jumped over.
	 * @param gameState current GameState
	 * @param fromPos Position to start the jump
	 * @return Collection of the pieces to jump over
	 */
	public static Collection<Piece> getAllPossibleJumpTurns(GameState gameState, Position fromPos) {
		Collection<Piece> neighbours = getAllNeighbouringPieces(gameState, fromPos);
		neighbours.removeIf(e -> !canJumpOver(gameState, fromPos, e));
		return neighbours;
	}
	
	/**
	 * Calculates the centre of the given positions.
	 * Returns the proximate position on the board.
	 * @param positions Collection of Positions
	 * @return centroid of given positions
	 */
	public static float[] getCentroid(Collection<Position> positions) {
		if (positions.isEmpty()) return null;
		float[] center = new float[2];
		Position centroid = positions.stream().
				map(p -> new Position(p)).
				reduce((p1, p2) -> new Position(p1.x + p2.x, p1.y + p2.y)).
				get();
		center[0] = centroid.x / (float)positions.size();
		center[1] = centroid.y / (float)positions.size();
		//System.out.println("centroid: ("+center[0]+","+center[1]+")");
		return center;
	}
	
	/**
	 * Calculates and returns the Euclidean distance between two given Positions. 
	 * @param fromPos first Position
	 * @param toPos second Position
	 * @return Euclidean distance between the given Positions
	 */
	public static float getDistance(Position fromPos, Position toPos) {
		float x = Math.abs(fromPos.x - toPos.x);
		float y = Math.abs(fromPos.y - toPos.y);
		return (float)Math.hypot(x, y);
	}
	
	/**
	 * Calculates and returns the Euclidean distance between two given Positions. 
	 * @param fromPos first Position, given as array with two values [x][y]
	 * @param toPos second Position, given as array with two values [x][y]
	 * @return Euclidean distance between the given Positions, 0 if the array have the wrong length
	 */
	public static float getDistance(float[] fromPos, float[] toPos) {
		if (fromPos.length != 2 || toPos.length != 2) {
			return 0;
		}
		float x = Math.abs(fromPos[0] - toPos[0]);
		float y = Math.abs(fromPos[1] - toPos[1]);

		return (float)Math.hypot(x, y);
	}


}
