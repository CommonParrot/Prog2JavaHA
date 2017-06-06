package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

/**
 * Represents a field position in game.
 * 
 * @author prog2-Team
 *
 */
public class Position {
	public final int x;
	public final int y;
	
	/**
	 * Constructor for a position
	 * 
	 * @param x x coordinate of a field position
	 * @param y y coordinate of a field position
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor for a position; takes x and y coordinates from given Piece.
	 * 
	 * @param p piece
	 */
	public Position(Piece p) {
		this(p.position.x, p.position.y);
	}

	/**
	 * Constructor for a position with given position.
	 * 
	 * @param position a given position
	 */
	public Position(Position position) {
		x = position.x;
		y = position.y;
	}
	
	/**
	 * Tests if the position is equal to the given one.
	 * 
	 * @param position position of a field position
	 * @return true if x and y values equal the given ones,
	 * 		   false if the values don't equal the given ones
	 */
	public boolean hasPosition(Position position) {
		return this.equals(position);
	}
	
	/**
	 * Overwritten equals method
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Position)) return false;
		Position p = (Position)o;
		if (x != p.x) return false;
		if (y != p.y) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return x*100+y;
	}
	
	/**
	 * Overwritten toString method
	 */
	public String toString() {
		String pos = "(";
		pos += x;
		pos += ","+ y +")";
		return pos;				
	}

}
