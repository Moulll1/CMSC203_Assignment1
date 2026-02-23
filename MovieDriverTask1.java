
import java.util.Scanner;

/*
 * Class: CMSC203 CRN:32324
 * Program: Lab 1 - Task 1 
 * Instructor: Grigory Grinberg
 * Description: A driver program that tests the Movie class by reading movie data from the user.
 * Due Date: 02/23/2026
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * Student: Moulaye Sidi
 */
public class MovieDriverTask1 {
public static void main(String[] args) {
// Setting up the scanner for user input
Scanner kbd = new Scanner(System.in);
// Instance of the Movie class
Movie m = new Movie();
// Getting movie details from the user
System.out.println("Enter movie title: ");
String movieTitle = kbd.nextLine();
m.setTitle(movieTitle);
System.out.println("Enter movie rating: ");
String movieRating = kbd.nextLine();
m.setRating(movieRating);
System.out.println("How many tickets were sold? ");
int tickets = kbd.nextInt();
m.setSoldTickets(tickets);
// Printing the summary using the toString method from Movie.java
System.out.println(m.toString());
System.out.println("Goodbye");
kbd.close();
	}
}