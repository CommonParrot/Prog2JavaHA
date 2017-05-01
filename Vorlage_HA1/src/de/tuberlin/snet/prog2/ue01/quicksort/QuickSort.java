package de.tuberlin.snet.prog2.ue01.quicksort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * itarative quicksort algorithm
 * @author prog2-team
 *
 */
public class QuickSort
{
	
	
	/**
	 * internal class for ranges 
	 */
	private static class QuickRange {
		public int left;
		public int right;
		
		public QuickRange(int left, int right) {
			this.left = left;
			this.right = right;
		}
		
		public String toString() {
			return "[" + left + "," + right + "]";
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private static int partition(int[] list, int left, int right) {
		int pivot = list[left + (right-left)/2];
		while (left <= right) {
			while (list[left] < pivot) {
				left++;
			}
			while (list[right] > pivot) {
				right--;
			}
			if (left <= right) {
				swap(list, left, right);
				left++;
				right--;
			}
		}

		return left;
	}

	private static void quickSort(int[] list, int left, int right) {
		LinkedList<QuickRange> rangeList = new LinkedList<QuickRange>();
		if (left >= right)
			return;

		rangeList.add(new QuickRange(left, right));

		while (true) {
			if (rangeList.size() == 0)
				break;
			left = rangeList.get(0).left;
			right = rangeList.get(0).right;
			rangeList.remove(0);

			int pivot = partition(list, left, right);
			
			if (pivot - 1 > left) {
				rangeList.add(new QuickRange(left, pivot - 1));
			}

			if (pivot < right) {
				rangeList.add(new QuickRange(pivot, right));
			}
		}
	}

	public static void sort(int[] list) {
		quickSort(list, 0, list.length-1);
	}
	
	/**
	 * splits array and distributes subarrays 
	 * to sorting threads
	 * then merges them back together 
	 * 
	 * @author Jakob Feldmann
	 */
	public static void sortMultithreaded(int[] arr2) {
		
		int piv = partition(arr2, 0, arr2.length-1);
		
		int[] left = new int[piv-1];
		int[] right = new int[arr2.length-piv];
		
		left = Arrays.copyOfRange(arr2, 0, piv);
		right = Arrays.copyOfRange(arr2, piv, arr2.length);
		
		ThreadSort sleft = new ThreadSort(left);
		ThreadSort sright = new ThreadSort(right); 
		
		sleft.run();
		sright.run();
		
		try {
			
			sleft.join();
			sright.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		int itl = 0;
		int itr = 0;
		
		for(int i : sleft.getArry()){
			arr2[itl] = i;
			itl++;
			itr++;
		}
		
		for(int x : sright.getArry()){
			arr2[itr] = x;
			itr++;
		}
		
	}
	
	
	public static void main (String[] args) {
		int[] newList = {5, 7, 23, 87, 11, 0, 5, 33, 30};
		for(int i: newList) {
			System.out.print(i + " ");
		}
		System.out.println();
		sortMultithreaded(newList);
		for(int i: newList) {
			System.out.print(i + " ");
		}
	}

}