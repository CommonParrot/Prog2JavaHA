package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Board;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Piece;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.PlaceType;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Position;


/**
 * Displays the actual field of the game.
 */
public class GameField extends JPanel {
	private static final long serialVersionUID = 2202084251467856749L;
	
	/**
	 * A field consists of a bunch of places (e.g. 19 x 25 places)
	 */
	final private GamePlace[][] field = new GamePlace[Board.dimensionX][Board.dimensionY];
	final private JPanel gameField = new JPanel();

	final private List<FieldListener> fieldListeners = new LinkedList<>();
	final private PlaceListener placeListener = new PlaceListener();
	
	/**
	 * Creates a new GameField, adds necessary GUI elements to it,
	 * and initialises it with blank {@link GamePlace}s.
	 */
	public GameField() {
		createGui();
		initBoard();
		this.addComponentListener(new SizeListener());
	}

	
	public JComponent getFieldView() {
		return gameField;
	}

	/**
	 * Creates a new GameField and adds necessary GUI elements to it.
	 */
	private void createGui() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(300,380));
		this.add(gameField);
		
		gameField.setLayout(new GridLayout(Board.dimensionY, Board.dimensionX));		
		for (int i=0; i<Board.dimensionY; i++)
			for (int j=0; j<Board.dimensionX; j++) {
				GamePlace place = new GamePlace(j, i);
				place.addMouseListener(placeListener);
				field[j][i] = place;				
				gameField.add(place);
			}
	}

	/**
	 * Initialises field with blank {@link GamePlace}s.
	 */
	private void initBoard() {
		for (Position place : Board.allPossibleLocations) {
			GamePlace myField = field[place.x][place.y];
			myField.placeType = PlaceType.Place;
			myField.piece = null;
			myField.showSelection = false;
//			field[place.x][place.y].showRecommendation = false;
		}
		
		for (Position h : Board.allHorizontalLines)
			field[h.x][h.y].placeType = PlaceType.HLine;

		for (Position ne : Board.allNorthEastLines)
			field[ne.x][ne.y].placeType = PlaceType.NorthEast;
		
		for (Position se : Board.allSouthEastLines)
			field[se.x][se.y].placeType = PlaceType.SouthEast;
	}
	
	/**
	 * Resizes the GameField such that the places stay (nearly) quadratic.
	 */
	private void makeQuadratic() {
		int h = Math.min(getWidth(), getHeight())-30;
		int w = (int) (h);
		Dimension dimension = new Dimension(w,h);
		gameField.setPreferredSize(dimension);
		gameField.revalidate();
	}
	
	class SizeListener extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			makeQuadratic();
		}
	}

	/**
	 * Removes all pieces on the board.
	 */
	public void clearPieces() {
		initBoard();
	}

	/**
	 * Sets a piece on the field. Therefore, the coordinates of
	 * the piece-object must be set appropriately.
	 * @param piece piece to be set
	 */
	public void setPiece(Piece piece) {
		if (piece.isPlaced)
			field[piece.position.x][piece.position.y].piece = piece;
	}

	/**
	 * Adds a FieldListener to the field.
	 * @param fieldListener FieldListener to be added
	 */
	public void addFieldListener(FieldListener fieldListener) {
		fieldListeners.add(fieldListener);
	}
	
	/**
	 * Removes a FieldListener from the field.
	 * @param fieldListener FieldListener to be removed
	 */
	public void removeFieldListener(FieldListener fieldListener) {
		fieldListeners.remove(fieldListener);
	}
	

	/**
	 * Informs all FieldListeners that a field was clicked
	 * @param x X coordinate of the clicked field
	 * @param y Y coordinate of the clicked field
	 */
	protected void fireFieldClicked(int x, int y) {
		for (FieldListener l : fieldListeners)
			l.fieldClicked(new Position(x, y));
	}
	
	/**
	 * Informs all FieldListeners that a field was entered
	 * @param x X coordinate of the entered field
	 * @param y Y coordinate of the entered field
	 */
	protected void fireFieldEntered(int x, int y) {
		for (FieldListener l : fieldListeners)
			l.fieldEntered(new Position(x, y));
	}
	
	/**
	 * Informs all FieldListeners that a field was exited
	 * @param x X coordinate of the exited field
	 * @param y Y coordinate of the exited field
	 */
	protected void fireFieldExited(int x, int y) {
		for (FieldListener l : fieldListeners)
			l.fieldExited(new Position(x, y));
	}
	
	
	/**
	 * Listens for mouse interaction with the Places
	 */
	class PlaceListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			GamePlace place = (GamePlace)e.getSource();
			fireFieldClicked(place.posX, place.posY);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			GamePlace place = (GamePlace)e.getSource();
			fireFieldEntered(place.posX, place.posY);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			GamePlace place = (GamePlace)e.getSource();
			fireFieldExited(place.posX, place.posY);
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}		
	}
	
	public void setSelection(Piece piece, Position lastPosition) {
		if (piece == null) return;
		int x = piece.position.x;
		int y = piece.position.y;
		
		if (lastPosition != null && !lastPosition.hasPosition(piece.position)) {
			field[x][y].piece = null;
			x = lastPosition.x;
			y = lastPosition.y;
		}
		
		field[x][y].piece = piece;
		field[x][y].showSelection = true;
	}

//	/**
//	 * Displays the AI recommendation 
//	 * 
//	 * @param lastPosition position to display the recommendation
//	 * @param benefit the benefit  for the field
//	 */
//	public void indicateRecommendation(Position lastPosition, float benefit) {
//		field[lastPosition.x][lastPosition.y].showRecommendation = true;
//		field[lastPosition.x][lastPosition.y].benefit = benefit;
//	}
}
