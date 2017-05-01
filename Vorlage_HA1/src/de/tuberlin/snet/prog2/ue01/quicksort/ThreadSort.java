package de.tuberlin.snet.prog2.ue01.quicksort;

/**
 * quicksorts a subarray
 * 
 * @author Jakob Feldmann
 */
public class ThreadSort extends Thread {

	int[] arry;
	
	public ThreadSort(int[] arr){
		
		arry = arr;
	}
	
	
	public void run(){
		
		QuickSort.sort(arry);
		
	}
	
	
	public int[] getArry() {
		return arry;
	}
	
	

}
