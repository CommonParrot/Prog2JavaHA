package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Piece;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.PieceColor;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.PlaceType;

/**
 * Represents a place on the {@link GameField}. A place is the smallest unit
 * of the field, where (at most) one piece can be placed.
 */
public class GamePlace extends JPanel {
	private static final long serialVersionUID = -7657078126767919378L;
	private static final Color COLOR_PLACE = Color.BLACK;
//	private static final Color COLOR_RECOMMENDATION = Color.RED;

	/**
	 * coordinates of the place
	 */
	final public int posX, posY;
	
	PlaceType placeType = PlaceType.Outside;
	Piece piece;
	boolean showSelection = false;
//	boolean showRecommendation = false;
	float benefit;
	
	/**
	 * Create a new instance with provided X and Y coordinates.
	 */
	GamePlace(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Method to paint the place inside the window. Makes use of
	 * helper methods for painting.
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		switch (placeType) {
		case Place:
			paintPlace(g, COLOR_PLACE);
			break;
		case Outside:
			// paint nothing/background color
			break;
		case HLine:
			addPaintHLine(g);
			break;
		case NorthEast:
			addPaintNorthEastLine(g);
			break;
		case SouthEast:
			addPaintSouthEastLine(g);
			break;
		default:
			break;
		}
		
		if (piece != null) {
			PieceColor color = piece.color;
			paintPieceShape(g, color);
		}
		
//		if (showRecommendation)
//			paintRecommendation(g);
	}
	
	private void addPaintSouthEastLine(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getWidth()/2, -getHeight()/2, getWidth()*3/2, getHeight()*3/2);
	}

	private void addPaintNorthEastLine(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(getWidth()/2, getHeight()*3/2, getWidth()*3/2, -getHeight()/2);
	}

	private void addPaintHLine(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
	}

	private void paintPlace(Graphics g, Color color) {
		g.setColor(color);
		int d = Math.min(getWidth(), getHeight());
		g.fillOval(getWidth()/2 - d/2, getHeight()/2 - d/2, d, d);	
		
	}
	
	private void paintPieceShape(Graphics g, PieceColor color) {
		int d = Math.min(getWidth(), getHeight()) * 3/4;
		int x = getWidth()/8;
		int y = getHeight()/8;
		
		g.setColor(color.getColor());
		g.fillOval(x, y, d, d);

		
		if (showSelection) {
			g.fillOval(0, 0, Math.min(getWidth(), getHeight()), Math.min(getWidth(), getHeight()));	
		}
		
		
	}

//	private void paintRecommendation(Graphics g) {
//		
//	}
	
}
