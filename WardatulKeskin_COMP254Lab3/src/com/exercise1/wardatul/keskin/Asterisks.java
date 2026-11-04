package com.exercise1.wardatul.keskin;
import java.util.Scanner;

public class Asterisks {
	// Print pattern method
	public static void printPattern(int current, int max) {
        if (current > max) { // Base case: Stop when current exceeds max
            return; 
        }

        // Print asterisks directly in the loop
        for (int i = 0; i < current; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Recursive call to increase pattern
        printPattern(current + 1, max);

        // Print again while recursion back (decreasing phase)
        if (current < max) { // Avoid duplicate printing of the peak
            for (int i = 0; i < current; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

	//------------------------------------WardatulKeskin|301294696-----------------------------------------
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the max number of asterisks in the pattern: ");
        int maxAsterisks = scanner.nextInt();

        if (maxAsterisks < 1) {
            System.out.println("Error: The number must be greater than 0.");
        } else {
            printPattern(1, maxAsterisks); // Start recursion from 1
        }
        scanner.close();
    }
}

