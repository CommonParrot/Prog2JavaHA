package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Position;

/**
 * This interface can be used to notify objects when a
 * field is clicked or the mouse hovers over it. 
 *
 */
public interface FieldListener {
	
	/**
	 * Called when a field is clicked
	 * @param position position of clicked field
	 */
	public void fieldClicked(Position position);
	/**
	 * Called when the mouse leaves a field
	 * @param position position of left field
	 */
	public void fieldExited(Position position);
	/**
	 * Called when the mouse enters a field
	 * @param x x-coordinate of entered field
	 * @param y y-coordinate of entered field
	 */
	public void fieldEntered(Position position);
	

}
