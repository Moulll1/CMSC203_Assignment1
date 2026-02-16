package gradecalculator; 
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/*
 * Class: CMSC203
 * Instructor: Grigory Grinberg
 * Description: A program to calculate student grades by reading configuration and input files.
 * Due: 02/16/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * Print your Name here: Moulaye Sidi
 */

public class GradeCalculator {

 public static void main(String[] args) {
        // Display the header first
 System.out.println("========================================");
 System.out.println(" CMSC203 Project 1 - Grade Calculator");
System.out.println("========================================\n");

        // Variables for inputs
        String courseName = "CMSC203 Computer Science I"; // Default
        int totalCategories = 3; // Default
        boolean isDefaultUsed = false;
        
        // We need scanners for files
Scanner configScanner = null;
Scanner dataScanner = null;

        // STEP 1: Try to open the configuration file
System.out.println("Loading configuration from gradeconfig.txt ...");
try {
File myConfig = new File("gradeconfig.txt");
configScanner = new Scanner(myConfig);
            
            // Read course info if file exists
 if (configScanner.hasNext()) {
                courseName = configScanner.nextLine();
                // Check if there is a number on the next line
                if (configScanner.hasNextInt()) {
                    totalCategories = configScanner.nextInt();
                }
            }
            System.out.println("Configuration loaded successfully.\n");
            
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file missing. Using defaults.");
            isDefaultUsed = true;
            // Defaults are already set above
        }

        // STEP 2: Process the student grade file
        System.out.println("Using input file: grades_input.txt");
        System.out.println("Using output file: grades_report.txt\n");
        System.out.println("Reading student scores...\n");

        try {
            File myInput = new File("grades_input.txt");
            dataScanner = new Scanner(myInput);

            // Read student name
            String fName = dataScanner.next();
            String lName = dataScanner.next();
            
            // Prepare the output string buffer to save later
            String finalOutput = "";
            String detailOutput = "";
            
            // Print basic info to console
            System.out.println("Student: " + fName + " " + lName);
            System.out.println("Course: " + courseName + "\n");
            System.out.println("Category Results:");

            double finalScore = 0.0;

            // Loop through each category
            for (int i = 0; i < totalCategories; i++) {
                // Read category data from input file
                String catName = dataScanner.next();
                int numItems = dataScanner.nextInt();
                
                // Determine weight. 
                // Since we cannot use Arrays, we read from config scanner simultaneously 
                // or use defaults if config failed.
                int weight = 0;
                
                if (!isDefaultUsed && configScanner != null) {
                    // Skip the name in config to get the number
                    configScanner.next(); 
                    weight = configScanner.nextInt();
                } else {
                    // Hardcoded defaults if config missing
                    if (i == 0) weight = 40;
                    else if (i == 1) weight = 30;
                    else weight = 30;
                }

                // Calculate average for this category
                double currentSum = 0;
                for (int j = 0; j < numItems; j++) {
                    currentSum += dataScanner.nextDouble();
                }
                
                double average = currentSum / numItems;
                
                // Add to final score (weighted)
                finalScore += (average * weight) / 100.0;

                // Create the string for this line
                String line = "  " + catName + " (" + weight + "%): average = " + String.format("%.2f", average);
                System.out.println(line);
                detailOutput += line + "\n";
            }
            
            // Close the config scanner since we are done with it
            if (configScanner != null) {
                configScanner.close();
            }

            // STEP 3: Ask user for +/- grading (Input Validation)
            Scanner userInput = new Scanner(System.in);
            String response = "";
            boolean valid = false;
            
            // Using a do-while loop for validation
            System.out.println(); // empty line
            do {
                System.out.print("Apply +/- grading? (Y/N): ");
                response = userInput.next();
                
                // Check if Y or N (case insensitive)
                if (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N")) {
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            } while (!valid);

            // STEP 4: Calculate Letter Grade
            String gradeLetter = "";
            if (finalScore >= 90) {
                gradeLetter = "A";
            } else if (finalScore >= 80) {
                gradeLetter = "B";
            } else if (finalScore >= 70) {
                gradeLetter = "C";
            } else if (finalScore >= 60) {
                gradeLetter = "D";
            } else {
                gradeLetter = "F";
            }
            
            String baseGrade = gradeLetter; // Keep the original for report

            // Apply +/- logic if user said Yes
            if (response.equalsIgnoreCase("Y")) {
                // Check last digit
                double remainder = finalScore % 10;
    // Logic: Top 30% is +, Bottom 30% is -
                if (gradeLetter.equals("A")) {
       if (finalScore < 93) gradeLetter = "A-"; 
                     // No A+ usually in standard 4.0 scale but let's follow standard logic
                } else if (!gradeLetter.equals("F")) {
                    if (remainder >= 7.0) {
                        gradeLetter += "+";
                    } else if (remainder < 3.0) {
                        gradeLetter += "-";
                    }
                }
            }

            // STEP 5: Final Output and File Writing
            System.out.println("\nOverall numeric average: " + String.format("%.2f", finalScore));
            System.out.println("Base letter grade: " + baseGrade);
            System.out.println("Final letter grade: " + gradeLetter);
            
            // Build the full report string
            String fullReport = "Student: " + fName + " " + lName + "\n" +
           "Course: " + courseName + "\n\n" +
            "Category Results:\n" + 
            detailOutput + "\n" +
            "Overall numeric average: " + String.format("%.2f", finalScore) + "\n" +
           "Base letter grade: " + baseGrade + "\n" +
             "Final letter grade: " + gradeLetter + "\n";

            // Write to file
            PrintWriter pw = new PrintWriter("grades_report.txt");
            pw.print(fullReport);
            pw.close();
            
            System.out.println("\nSummary written to grades_report.txt");
            System.out.println("Program complete. Goodbye!");
            
            // Close remaining scanners
            dataScanner.close();
            userInput.close();

        } catch (Exception e) {
            System.out.println("Error: grades_input.txt not found. Exiting.");
        }
    }
}