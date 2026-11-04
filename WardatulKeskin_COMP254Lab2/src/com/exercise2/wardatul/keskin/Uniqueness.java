/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.exercise2.wardatul.keskin;

import java.util.Arrays;

/**
 * Demonstration of algorithms for testing element uniqueness.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Uniqueness {

  /** Returns true if there are no duplicate elements in the array. */
  public static boolean unique1(int[] data) {
    int n = data.length;
    for (int j=0; j < n-1; j++)
      for (int k=j+1; k < n; k++)
        if (data[j] == data[k])
          return false;                    // found duplicate pair
    return true;                           // if we reach this, elements are unique
  }
  /*Break down of big oh notation
   *for (int j=0; j < n-1; j++) = n-1 - 1 - 0 + 1 = n - 1 operation
   *total nested loops on (n - j - 1) = n(n-1)/2 = (n^2-n)/2
   *Big Oh Notation = O(n^2)  */

  /** Returns true if there are no duplicate elements in the array. */
  public static boolean unique2(int[] data) {
    int n = data.length;
    int[] temp = Arrays.copyOf(data, n);   // make copy of data
    Arrays.sort(temp);                     // and sort the copy
    for (int j=0; j < n-1; j++)
      if (temp[j] == temp[j+1])            // check neighboring entries
        return false;                      // found duplicate pair
    return true;                           // if we reach this, elements are unique
  }
  /*Break down of big oh notation
   *copying data = O(n)
   *sorting data = O(n log n)
   *Loops = O(n)
   *Big Oh Notation = O(n log n)  */
//------------------------------------WardatulKeskin|301294696-----------------------------------------
  public static void main(String[] args) {
	  // For unique 1
	  /*int maxTime = 600; // Maximum running time in milliseconds
      int low = 100;
      int high = 200000;
      System.out.println("Testing unique1...");
      
      int bestN = findMaxN(low, high, maxTime);
      System.out.println(String.format("The largest value of n that completes in %d milliseconds or less is %d", maxTime, bestN));*/
	  // For unique 2
      int maxTime = 600; // Maximum running time in milliseconds
      int low = 100000;
      int high = 500000000;
      System.out.println("Testing unique2...");
      
      int bestN = findMaxN(low, high, maxTime); // Start with a default bestN
      System.out.println(String.format("The largest value of n that completes in %d milliseconds or less is %d", maxTime, bestN));
  }
  // Binary search to find the largest value n
  public static int findMaxN(int low, int high, int maxTime) {
	  // Initialize bestN with the lower bound
      int bestN = low; 

      while (low <= high) {
          int mid = (low + high) / 2; // Calculate the midpoint
          int[] data = new int[mid]; // Create an array of size mid
          for (int i = 0; i < mid; i++) data[i] = i; // Populate array with unique values 
          long startTime = System.currentTimeMillis(); // Record the start time
          unique2(data); // Execute unique1 or unique 2
          long endTime = System.currentTimeMillis(); // Record the end time
          long elapsed = endTime - startTime; // Calculate elapsed time
          System.out.println(String.format("n: %9d took %12d milliseconds", mid, elapsed));
          
          // Check if the elapsed time is within the maximum time limit
          if (elapsed <= maxTime) {
              bestN = mid; // // Update bestN to the current mid value
              low = mid + 1; // Search in the upper half
          } else {
              high = mid - 1; // Search in the lower half
          }
      }
      return bestN;
  }
} 
