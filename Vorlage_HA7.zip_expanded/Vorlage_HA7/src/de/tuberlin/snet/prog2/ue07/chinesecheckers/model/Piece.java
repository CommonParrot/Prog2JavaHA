package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;


public class Piece {
	
	final public PieceColor color;
	public boolean isPlaced;
	public Position position;
	
	/**
	 * Constructor for a piece with the given color.
	 * The piece is not placed and has no position, yet.
	 * @param color
	 */
	public Piece(PieceColor color) {
	    this.color = color;
	    reset();
	  }

	/**
	 * Resets all attributes of a piece.
	 */
	public void reset() {
		isPlaced = false;
		position = new Position(-1, -1);
	}
	
	/**
	 * Tests if the piece stands on a given position.
	 * 
	 * @param x x coordinate of field position
	 * @param y y coordinate of field position
	 * @return true if piece stands on given field position,
	 * 		   false if piece doesn't stand on given field position
	 */
	public boolean hasPosition(Position givenPosition) {
		if (!position.equals(givenPosition)) return false;
		return true;
	}
}
