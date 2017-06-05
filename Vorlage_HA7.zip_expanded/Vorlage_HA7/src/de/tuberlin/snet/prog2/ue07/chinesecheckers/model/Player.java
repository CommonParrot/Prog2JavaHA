package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a player in game.
 * 
 * @author prog2-Team
 *
 */
public class Player {
	
	/** Player's name */
	private String name = "";
	/** Player's color */
	private PieceColor color;
	/** List of owning pieces */
	private List<Piece> pieces = new LinkedList<>();
	/** Player's start corner */
	private Board.Corner startCorner;
	/** Player's destination corner */
	private Board.Corner destinationCorner;
	


	/**
	 * Constructor of player
	 * 
	 * @param name player name
	 * @param color player color
	 */
	public Player(String name, PieceColor color) {
		this.name = name;
		this.color = color;
		startCorner = color.start;
		destinationCorner = color.destination;
	}

	/**
	 * Returns the Board.Corner enum value for the destination corner.
	 * @return destination corner of this player
	 */
	public Board.Corner getDestinationCorner() {
		return destinationCorner;
	}
	
	/**
	 * Returns the Board.Corner enum value for the start corner.
	 * @return start corner of this player
	 */
	public Board.Corner getStartCorner() {
		return startCorner;
	}
	
	/**
	 * Returns the name of this player.
	 * 
	 * @return player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the color of this player
	 * 
	 * @return player color
	 */
	public PieceColor getColor() {
		return color;
	}

	/**
	 * Returns the pieces of this player.
	 * 
	 * @return list of pieces in game belong to this player
	 */
	public List<Piece> getPieces() {
		return pieces;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Player))
			return false;
		
		Player p = (Player) obj;
		boolean equal = true;
		equal &= this.getName().equals(p.getName());
		equal &= this.getColor().equals(p.getColor());
		for (int i=0; i<getPieces().size(); i++)
			equal &= getPieces().get(i).equals(p.getPieces().get(i));
		return equal;
	}
	
	/**
	 * Method to initialise a number of pieces for the player.
	 * 
	 * @param numOfPieces given number of pieces
	 */
	public void initPieces() {
		pieces.clear();
		for (Position pos : startCorner.locations()) {
			Piece piece = new Piece(color);
			piece.position = pos;
			piece.isPlaced = true;
			pieces.add(piece);
		}
	}

	/**
	 * Method to add a new piece to player's piece list.
	 * 
	 * @param p a new piece
	 */
	public void addPiece(Piece p) {
		pieces.add(p);
	}

	/**
	 * Overwritten toString method
	 */
	public String toString() {	
		String s = "[";
		s += name + ",";
		s += color.name();
		if (pieces.isEmpty())
			return s+"]";
		
		s += ",{";		
		Iterator<Piece> it = pieces.iterator();		
		s += it.next().toString();
		while (it.hasNext())
			s += "," + it.next().toString();
			
		
		s += "}]";
		return s;
	}

}
