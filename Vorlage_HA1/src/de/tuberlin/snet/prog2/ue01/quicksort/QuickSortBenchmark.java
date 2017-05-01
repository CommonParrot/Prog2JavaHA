package de.tuberlin.snet.prog2.ue01.quicksort;

import java.util.Arrays;

public class QuickSortBenchmark {
	/************************************* Utilities ********************************************************/
	public static boolean isSorted(int[] array) {
		int currentMax = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			if (currentMax > array[i]) {
				return false;
			}
			currentMax = array[i];
		}

		return true;

	}

	/************************************  Utilities ********************************************************/

	public static void main(String[] args) {

		System.out.println("*** initializing ***");
		int length = 9999999;
		int[] arr1 = new int[length];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = (int) (Math.random()*100);
		}
		int[] arr2 = Arrays.copyOf(arr1, arr1.length);

		System.out.println("***     done     ***");
		System.out.println("********************");
		System.out.println();
		System.out.println("*** benchmarking ***");
		System.out.println("#floats: " + length);

		long time1 = System.currentTimeMillis();
		QuickSort.sort(arr1);
		time1 = System.currentTimeMillis() - time1;

		long time2 = System.currentTimeMillis();
		QuickSort.sortMultithreaded(arr2);
		time2 = System.currentTimeMillis() - time2;

		 System.out.println("Array 1 sorted: "+isSorted(arr1));
		 System.out.println("Array 2 sorted: "+isSorted(arr2));

		System.out.println("Singlethreaded Quicksort:   " + time1 + "ms");
		System.out.println("Multithreaded Quicksort:    " + time2 + "ms");
		System.out.println("***     done     ***");
		
//		for (int i: arr2)
//			System.out.print(i + " ");
	}
}
