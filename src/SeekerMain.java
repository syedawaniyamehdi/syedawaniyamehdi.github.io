/**
 * Class SeekerMain is the driver program for the seeker management task. It
 * reads names from a file, shuffles them if the user so desires, and uses them
 * to start the game. The user is asked for the name of the next target until
 * the game is over.
 * 
 * @author Diane Mueller modified from Stuart Reges
 *
 */

import java.io.*;
import java.util.*;

public class SeekerMain {
	public static void main(String[] args) throws FileNotFoundException {
		// prompt for file name
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to Seeker");
		System.out.println();
		System.out.print("What file do you want to use this time? ");
		String fileName = console.nextLine().strip();

		// read names into a list, using a Set to avoid duplicates
		Scanner input = new Scanner(new File(fileName));
		Set<String> names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		List<String> names2 = new ArrayList<String>();
		while (input.hasNextLine()) {
			String name = input.nextLine().strip();
			if (name.length() > 0 && !names.contains(name)) {
				names.add(name);
				names2.add(name);
			}
		}

		// shuffle if desired
		if (yesTo(console, "Do you want the names shuffled?")) {
			Collections.shuffle(names2);
		}
		// make an immutable version and use it to build a SeekerManager
		List<String> names3 = Collections.unmodifiableList(names2);
		SeekerManager manager = new SeekerManager(names3);
		System.out.println();
		
        // test code
      System.out.println("Initial seeking ring: ");
      manager.printSeekingRing();
      System.out.println("\n\n");

		// prompt the user for targets until the game is over
		while (!manager.gameOver())
			foundOne(console, manager);

		// report who won
		manager.printSeekingRing();
		System.out.println("Final captured list is as follows:");
		manager.printCapturedList();
	}

	/**
	 * Handles the details of recording one captured target. Shows the current
	 * seeking ring and captured list to the user. Prompts for a name and
	 * records the captured target if the name is legal.
	 * 
	 * @param console
	 *            - facilitates input from the keyboard
	 * @param manager
	 *            - manages the seeking ring and captured list
	 */
	public static void foundOne(Scanner console, SeekerManager manager) {
		System.out.println("Current seeking ring:");
		manager.printSeekingRing();
		System.out.println("Current captured list:");
		manager.printCapturedList();
		System.out.println();
		System.out.print("next target? ");
		String name = console.nextLine().strip();
		if (manager.capturedListContains(name))
			System.out.println(name + " was already captured.");
		else if (!manager.seekingRingContains(name))
			System.out.println("Unknown person.");
		else
			manager.captured(name);
		System.out.println();
	}

	/**
	 * asks the user a question, forcing an answer of "y" or "n";
	 * 
	 * @param prompt
	 *            String for the question prompt
	 * @param console
	 *            Scanner to facilitate input
	 * @return true if the answer was y, returns false otherwise
	 */
	public static boolean yesTo(Scanner console, String prompt) {
		System.out.print(prompt + " (y/n)? ");
		String response = console.nextLine().strip().toLowerCase();
		while (!response.equals("y") && !response.equals("n")) {
			System.out.println("Please answer y or n.");
			System.out.print(prompt + " (y/n)? ");
			response = console.nextLine().strip().toLowerCase();
		}
		return response.equals("y");
	}
}
