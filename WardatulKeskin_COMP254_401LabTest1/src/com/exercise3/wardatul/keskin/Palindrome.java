package com.exercise3.wardatul.keskin;

import java.util.Scanner;


public class Palindrome {
	public static boolean isPalindrome(String str, int left, int right) {
        // Base case: If left index crosses right, it's a palindrome
        if (left >= right) {  // O(n)
            return true; //  O(n)
        }
        // If characters don't match, it's not a palindrome
        if (str.charAt(left) != str.charAt(right)) {  // O(n)
            return false; //  O(n)
        }
        // Recursive call for left and right
        return isPalindrome(str, left + 1, right - 1); //  O(n)
    }

	//------------------------Wardatul Keskin | 301294696-------------------------------------
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        // Input reading operation (O(n))
        System.out.print("Enter a string to check if it's a palindrome: ");
        String input = scanner.nextLine();
        scanner.close();

        // Removing spaces and converting case-insensitive comparison
        // replaceAll() and toLowerCase() both take O(n), total O(n)
        input = input.replaceAll("\\s+", "").toLowerCase();

        // Calling the optimized recursive palindrome check
        if (isPalindrome(input, 0, input.length() - 1)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is NOT a palindrome.");
        }
    }

}
