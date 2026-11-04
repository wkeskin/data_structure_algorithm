package com.exercise2.wardatul.keskin;
import java.util.Scanner;

public class Vowels {
	// Recursive method to count vowels in a string
    /*public static int countVowels(String str, int index) {
        if (index == str.length()) { // Base case: Reached end of the string
            return 0; 
        }

        char c = Character.toLowerCase(str.charAt(index)); // Convert to lowercase
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0; // Checking and counting vowel

        return count + countVowels(str, index + 1); // Recursive call // use the binary 
    }

  //------------------------------------WardatulKeskin|301294696-----------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Call the recursive method starting from first character
        int vowelCount = countVowels(input, 0);

        // Display result
        System.out.println("Number of vowels in the string: " + vowelCount);

        scanner.close();
	}*/
	public static int countVowels(String str, int start, int end) {
		// Base case 1 : Single character
        if (start == end) {
            char c = Character.toLowerCase(str.charAt(start));
            return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        }

        // Base case 2 : Empty string or invalid range
        if (start > end) {
            return 0;
        }

        // Find the middle index
        int mid = start + (end - start) / 2;

        // Recursively count vowels in the left and right halves
        int leftCount = countVowels(str, start, mid);
        int rightCount = countVowels(str, mid + 1, end);

        // Combine the results
        return leftCount + rightCount;
    }
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Call the recursive method using binary recursion
        int vowelCount = countVowels(input, 0, input.length() - 1);

        // Display result
        System.out.println("Number of vowels in the string: " + vowelCount);

        scanner.close();
    }
}
