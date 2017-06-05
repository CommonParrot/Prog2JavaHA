package de.tuberlin.snet.prog2.ue07.chinesecheckers.model;

import java.awt.Color;

public enum PieceColor {
	BLUE(Color.BLUE, Board.Corner.North, Board.Corner.South), GREEN(Color.GREEN, Board.Corner.SouthWest, Board.Corner.NorthEast), RED(Color.RED, Board.Corner.SouthEast, Board.Corner.NorthWest);
	Color value;
	Board.Corner start;
	Board.Corner destination;
	private static PieceColor[] vals = values();
	 
	private PieceColor(Color value, Board.Corner start, Board.Corner destination) {
		this.value = value;
		this.start = start;
		this.destination = destination;
	}
	
	public static PieceColor getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}
	
	public PieceColor next() {
		return vals[(this.ordinal()+1) % vals.length];
	}
	
	public Color getColor() {
		return value;
	}
	
	public static Color[] getColors() {
		Color[] cols = new Color[vals.length];
		for (int i = 0; i<vals.length; i++)
			cols[i] = vals[i].getColor();
		return cols;
	}
	
	public static PieceColor getPieceColor(Color color) {
		for (PieceColor p : vals)
			if (p.getColor()==color)
				return p;
		return null;
	}
}