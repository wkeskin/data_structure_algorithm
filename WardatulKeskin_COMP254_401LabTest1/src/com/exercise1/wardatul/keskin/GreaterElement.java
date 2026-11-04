package com.exercise1.wardatul.keskin;

import java.util.Arrays;
import java.util.Scanner;

public class GreaterElement {
	
	public static int binarySearch(int[] arr, int x) {
        int left = 0, right = arr.length - 1;
        while (left <= right) { //  O(log n)
            int mid = left + (right - left) / 2; // O(1)
            if (arr[mid] <= x) { 
                left = mid + 1; // O(1)
            } else {
                right = mid - 1; // O(1)
            }
        }
        return left; // First element greater than x
    }

    public static int[] findElementsGreaterThanX(int[] arr, int x) {
        Arrays.sort(arr); // Sort the array (O(n log n))
        int index = binarySearch(arr, x); // Perform binary search (O(log n))
        
        return Arrays.copyOfRange(arr, index, arr.length); // Return remaining elements, (O(m), where m is the number of elements greater than x)
    }

  //------------------------Wardatul Keskin | 301294696-------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        int[] arr = {-2, 1, 4, 8, 15, 6, 12};
        
        System.out.print("Enter value of X: ");
        int x = scanner.nextInt();
        
        int[] result = findElementsGreaterThanX(arr, x);
        
        System.out.println("Elements greater than " + x + ": " + Arrays.toString(result));
        scanner.close();
	}

}
