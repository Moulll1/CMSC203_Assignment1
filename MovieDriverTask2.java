import java.util.Scanner;

/*
 * Class: CMSC203 CRN:32324
 * Program: Lab 1 - Task 2 
 * Instructor: Grigory Grinberg
 * Description: A driver program that tests the Movie class by reading movie data from the user.
 * Due Date: 02/23/2026
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * Student: Moulaye Sidi
 */
public class MovieDriverTask2 {
public static void main(String[] args) {
// Using 'kbd' as a short name for keyboard
Scanner kbd = new Scanner(System.in);
String choice; 
do {
// Creating the movie object inside the loop
Movie m = new Movie();
System.out.println("Enter the name of a movie");
String name = kbd.nextLine();
m.setTitle(name);
System.out.println("Enter the rating of the movie");
String rate = kbd.nextLine();
m.setRating(rate);
System.out.println("Enter the number of tickets sold for this movie");
int ticketsSold = kbd.nextInt();
m.setSoldTickets(ticketsSold);
// Showing the results
System.out.println(m.toString());
// THIS IS IMPORTANT: Clears the scanner buffer
kbd.nextLine(); 
System.out.println("Do you want to enter another? (y or n)");
choice = kbd.nextLine();
} while (choice.equalsIgnoreCase("y"));
System.out.println("Goodbye");
kbd.close();
	}
}