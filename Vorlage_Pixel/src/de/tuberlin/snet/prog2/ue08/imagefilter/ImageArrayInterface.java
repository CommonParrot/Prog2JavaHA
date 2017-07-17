package de.tuberlin.snet.prog2.ue08.imagefilter;

public interface ImageArrayInterface<E> {

	public E get(int x, int y);
	public void add(int x, int y, E element);
	public int getWidth();
	public void setWidth(int width);
	public int getHeight();
	public void setHeight(int height);
	public int getIndex(int x, int y);
	
}
