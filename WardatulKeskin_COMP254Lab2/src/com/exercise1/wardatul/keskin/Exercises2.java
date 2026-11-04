package com.exercise1.wardatul.keskin;

public class Exercises2 {

	//running time is:  
	/** Returns the sum of the integers in given array. */
	public static int example1(int n) {
		int sum = 0; // 1 operation
	    for (int i=1; i <= n; i++)     // n times operations, (n - 1 + 1 = n)
	      sum += i; // n times operations
	    return sum; // 1 operation
	}
	/* f(n) = 1 + n + n + 1 = 2 + 2n
	 * Big Oh Notation = O(n)*/
	//
	public static int example2(int n) {
	    int p = 1; // 1 operation
	    for (int i=1; i <= 2*n; i++)    // 2n operations, (2n - 1 + 1 = 2n)
	      p *= i; // 2n operations
	    return p; // 1 operation
	}
	/* f(n) = 1 + (2n) + (2n) + 1 = 2 + 4n
	 * Big Oh Notation = O(n)*/
	//
	public static int example3(int n) {
	    int p = 1; // 1 operation
	    for (int i=1; i <= Math.pow(n,2); i++) // n^2 operations, (n^2 - 1 + 1 = n^2)       
	      p *= i; // n^2 operations
	    return p; // 1 operation
	}
	/*f(n) = 1 + n^2 + n^2 + 1 = 2 + 2(n^2)
	 * Big Oh Notation = O(n^2)*/
  
	//
	public static int example4(int n) {
	    int sum = 0; // 1 operation
	    for (int i=1; i <= 2*n; i++) // 2n iterations, (2n - 1 + 1 = 2n)
	    {
	    	for(int j=1;j<=i; j++) // i operations
	    		sum +=i; // i times
	    }
    	/*Break down calculation for nested loop
    	 * i = 2n
    	 * summation formula = (m(m+1))/2 or f(n) = sigma ain^i
    	 * 2n(2n+1)/2 = (4n^2 + 2n)/2
    	 * final nested loop 2n^2 + n*/
	    return sum; // 1 operation
	}
	/*f(n) = 1 + (2n^2 + n) + 1 = 2 + 2n^2 + n
	 * Big Oh Notation = O(n^2) */
	//
	public static int example5(int n) {
	    int sum = 0; // 1 operation
	    for (int i=1; i <= Math.pow(n,2); i++) // n^2 operations, (n^2 - 1 + 1 = n^2)
	    {
	    	for(int j=1;j<=i; j++) // i operations
	    		sum +=i; // i times
	    }
	    /*Break down calculation for nested loop
	     * i = n^2
	     * summation formula = (m(m+1))/2 or f(n) = sigma ain^i
	     * n^2(n^2+1)/2 = (n^4 + n^2)/2*/
	    return sum; // 1 operation
	}
	/*f(n) = 1 + (n^4 + n^2)/2 + 1 = 2 + (n^4 + n^2)/2
	 * Big Oh Notation = O(n^4)*/
}
//------------------------------------WardatulKeskin|301294696-----------------------------------------
