package com.exercise3.wardatul.keskin;

public class NonSummableArray {
	// Find the maximum number in the array
	public static int findMax(int[] data) {
		int max = Integer.MIN_VALUE; // 1 operation
	    for (int num : data) { // n operation (n depends on the size of array)
	    	if (num > max) { // 1 operation
	        max = num; // 1 operation
	      }
	    }
	    return max; // 1 operation
	  }
	  
	  /* f(n) = 1 + n + 1 + 1 + 1 = 4 + n 
	   * Big O Notation = O(n)
	   */

	  // Find the non-possible summation of two integers in the array
	public static int findNonPossibleSum(int[] data) {
		int max = findMax(data); // n operation
	    return max * 2 + 1; // 1 operation
	  }
	  /* f(n) = 1 + n
	   * Big O Notation = O(n)
	   * Total complexity of NonSummableArray = O(n)
	   */
	//------------------------------------WardatulKeskin|301294696-----------------------------------------
	public static void main(String[] args) {
		// For all negatives elements
		// int[] data = {-2, -4, -7, -5, -10, -1};
		
		// For all positive elements
		// int[] data = {4, 7, 1, 2, 9, 10, 8};
		
		// For combined positives and negatives elements
		int[] data = {-5, 4, 2, -1, 6};

	    int max = findMax(data);
	    System.out.println("Maximum number: " + max);

	    int nonPossibleSum = findNonPossibleSum(data);
	    System.out.println("Non-possible summation of two integers: " + nonPossibleSum);
	  }

	}
