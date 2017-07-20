package de.tuberlin.snet.prog2.ue08.imagefilter;

import java.util.ArrayList;

public class ImageArray extends ArrayList<Pixel> implements ImageArrayInterface<Pixel> {
	
	int height;
	int width;
	
	@Override
	public Pixel get(int x, int y) {

		return this.stream().filter(p -> p.x == x && p.y == y).findFirst().orElse(new Pixel(-1,-1, null));

	}
	
	public ImageArray(double h, double w){
		
		this.height = (int) h;
		this.width = (int) w;
		
	}

	@Override
	public int getWidth() {
	
		return this.width;
	}

	@Override
	public void setWidth(int width) {
		
		this.width = width;

	}

	@Override
	public int getHeight() {
		
		return this.height;
		
	}

	@Override
	public void setHeight(int height) {
		
		this.height = height;

	}

	@Override
	public int getIndex(int x, int y) {
		
		return this.indexOf(this.get(x,y));
		
	}

	@Override
	public void add(int x, int y, Pixel element) {
		
	}

}
