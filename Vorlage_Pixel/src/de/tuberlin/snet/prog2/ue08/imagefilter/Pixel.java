package de.tuberlin.snet.prog2.ue08.imagefilter;

import javafx.scene.paint.Color;

/**
 * Class represents a pixel with coordinates x and y and a color.
 * @author prog2-team
 *
 */
public class Pixel {
	
	/** x-coordinate of the pixel*/
	int x;
	/** y-coordinate of the pixel*/
	int y;
	/** color of the pixel*/
	Color color;
	
	/**
	 * Creates a pixel object.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param rgb color
	 */
	public Pixel(int x, int y, Color rgb) {
		this.x = x;
		this.y = y;
		color = rgb;
	}
	
	/**
	 * Creates a copy of the given pixel but with a new color.
	 * @param p pixel to copy
	 * @param c new color
	 * @return pixel with the same coordinates and a new color
	 */
	public static Pixel copy(Pixel p, Color c) {
		Pixel pixel = copy(p);
		pixel.color = c;
		return pixel;
	}
	
	/**
	 * 
	 * @return the color of this pixel
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * 
	 * @param p
	 * @return sopy of the given pixel
	 */
	private static Pixel copy(Pixel p) {
		return new Pixel(p.x, p.y, p.color);
	}
	
	public String toString(){
		return "(" + x + "," + y + "): " + color.toString();
	}

}
