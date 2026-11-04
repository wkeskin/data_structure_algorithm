package exercise02;

import java.util.Scanner;

public class Reversal {
	public static String reverseString(String str, int start, int end) {
        // Base case: Single character or empty string
        if (start >= end) {
            return str;
        }

        // Swap characters at start and end
        char[] charArray = str.toCharArray();
        char temp = charArray[start];
        charArray[start] = charArray[end];
        charArray[end] = temp;

        // Convert back to string and recursively call with inner substring
        return reverseString(new String(charArray), start + 1, end - 1);
    }

//------------------------Wardatul Keskin | 301294696-------------------------------------

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter a string to reverse: ");
        String input = scanner.nextLine();
        scanner.close();

        // Call the recursive function with initial indices
        String reversed = reverseString(input, 0, input.length() - 1);

        // Print the reversed string
        System.out.println("Reversed: " + reversed);
	}

}
