package com.exercise3.wardatul.keskin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSearch {
	// Recursive method to search for files by name
	 public static List<String> searchFiles(File directory, String filename) {
	        List<String> results = new ArrayList<>(); // Store matching file paths

	        // Base Case 1: Directory doesn't exis
	        if (!directory.exists()) {
	            System.out.println("Skipping: " + directory.getAbsolutePath() + " (Does not exist)");
	            return results; // Return empty list if directory does not exist
	        }

	        
	        if (directory.isDirectory()) {
	            File[] files = directory.listFiles(); // Get all child files and directories

	            // Base Case 2: Permission Issues
	            if (files == null) {
	                return results; // Return empty list if access is restricted
	            }

	            for (File file : files) {
	                if (file.isDirectory()) {
	                    results.addAll(searchFiles(file, filename)); // Recursive call for subdirectories
	                } else if (file.getName().toLowerCase().contains(filename.toLowerCase())) { // Substring match
	                    results.add(file.getAbsolutePath()); // Add matching file path to results
	                }
	            }
	        }

	        return results; // Return the collected file paths
	    }

	//------------------------------------WardatulKeskin|301294696-----------------------------------------
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

        // Get directory path from user
        System.out.print("Enter the directory path to search in: ");
        String path = scanner.nextLine().trim();

        // Get file name or keyword from user
        System.out.print("Enter the file name to search for: ");
        String keyword = scanner.nextLine().trim();

        File rootDir = new File(path);
        List<String> foundFiles = searchFiles(rootDir, keyword);

        // Display results
        if (foundFiles.isEmpty()) {
            System.out.println("No files containing '" + keyword + "' were found in " + path);
        } else {
            System.out.println("Matching file(s) found:");
            for (String filePath : foundFiles) {
                System.out.println(filePath);
            }
        }

        scanner.close();
	}

}
