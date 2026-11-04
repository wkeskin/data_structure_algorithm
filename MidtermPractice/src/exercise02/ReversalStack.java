package exercise02;

import java.util.Stack;
import java.util.Scanner;

public class ReversalStack {
	/** Recursive method to pop elements from stack and form reversed string */
    private static void reverseUsingStack(Stack<Character> stack, StringBuilder reversed) {
        // Base case: If the stack is empty, stop recursion
        if (stack.isEmpty()) {
            return;
        }

        // Pop the top character and append to result
        char ch = stack.pop();
        reversed.append(ch);

        // Recursive call
        reverseUsingStack(stack, reversed);
    }

    /** Method to reverse string using recursion and stack */
    public static String reverse(String str) {
        Stack<Character> stack = new Stack<>();

        // Push all characters onto the stack
        for (char c : str.toCharArray()) {
            stack.push(c);
        }

        // StringBuilder to store the reversed string
        StringBuilder reversed = new StringBuilder();

        // Recursive function to construct the reversed string
        reverseUsingStack(stack, reversed);

        return reversed.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter a string to reverse: ");
        String input = scanner.nextLine();
        scanner.close();

        // Reverse using stack-based recursion
        String reversed = reverse(input);

        // Print the reversed string
        System.out.println("Reversed: " + reversed);
    }
}