
/**
 * Name:Waniya Mehdi
 * Date: 15th nov 2023
 * CSC 202--Program Project 3
 *
 * SeekerManager manages two lists of nodes, the seeking ring and
 * the captured list. When a person is captured from the seeking ring, the
 * person's node is moved to the captured list and the name of the capturer is
 * added to the captured person's node. The last person in the seeking ring
 * is the winner.
 * 
 * Citations of Assistance (who and what OR declare no assistance)
 * Prof. Mueller helped me avoid a circular linked list in printSeekingRing()
 * Prof.Mueller also helped me 	with strategizing for the captured method, I also discussed the strategy for captured with Solyana.
 * 
 */

import java.util.List;

public class SeekerManager {

	private SeekerNode seekerRingFront;
	private SeekerNode capturedListFront;

	/**
	 * constructs the seekerManager object
	 * 
	 * @param names- the names that are to be in the SeekingRing
	 */
	public SeekerManager(List<String> names) {
		if (names == null || names.size() == 0) {
			throw new IllegalArgumentException();
		} else {
			seekerRingFront = new SeekerNode(names.get(0));
			SeekerNode currentNode = seekerRingFront;
			for (int i = 1; i < names.size(); i++) {
				currentNode.next = new SeekerNode(names.get(i));
				currentNode = currentNode.next;
			}
			capturedListFront = null;
		}

	}

	/**
	 * prints out the seeking ring
	 */
	public void printSeekingRing() {

		SeekerNode currentNode = seekerRingFront;
		if (gameOver()) {
			System.out.println(" " + currentNode.name + " won the game!");
		} else {
			while (currentNode.next != null) {
				System.out.println("  " + currentNode.name + " is seeking " + currentNode.next.name);
				currentNode = currentNode.next;
			}
			System.out.println("  " + currentNode.name + " is seeking " + seekerRingFront.name);
		}

	}

	/**
	 * prints out the captured ring
	 */
	public void printCapturedList() {

		if (capturedListFront == null) {
			System.out.println("No one has been captured.");
		} else {
			SeekerNode currentNode = capturedListFront;
			while (currentNode != null) {
				System.out.println("  " + currentNode.name + " was captured by " + currentNode.capturedBy + ".");
				currentNode = currentNode.next;
			}
		}
	}

	/**
	 * Checks whether the SeekingRing contains a node
	 * 
	 * @param name - the name of the person we're searching for
	 * @return true if seekingRing contains the node, false if not
	 */
	public boolean seekingRingContains(String name) {
		SeekerNode currentNode = seekerRingFront;

		while (currentNode != null) {
			if (currentNode.name.equalsIgnoreCase(name)) {
				return true;
			} else {
				currentNode = currentNode.next;

			}

		}

		return false;
	}

	/**
	 * Checks whether the capturedList contains a node
	 * 
	 * @param name - the name of the person we're searching for
	 * @return true if capturedList contains the node, false if not
	 */
	public boolean capturedListContains(String name) {
		SeekerNode currentNode = capturedListFront;

		while (currentNode != null) {
			if (currentNode.name.equalsIgnoreCase(name)) {
				return true;
			} else {
				currentNode = currentNode.next;

			}

		}

		return false;
	}

	/**
	 * ends the game
	 * 
	 * @return true if ended, false if not
	 */
	public boolean gameOver() {
		if (seekerRingFront == null || seekerRingFront.next == null) {
			return true;
		}
		return false;
	}

	/**
	 * determines who has been captured and who hasn't
	 * 
	 * @param name- the person user wants to capture.
	 */
	public void captured(String name) {
		if (gameOver()) {
			throw new IllegalStateException();
		}

		if (!seekingRingContains(name)) {
			throw new IllegalArgumentException();
		}

		SeekerNode currentNode = seekerRingFront;
		SeekerNode prevNode = null;

		while (!currentNode.name.equalsIgnoreCase(name)) {
			prevNode = currentNode;
			currentNode = currentNode.next;
		}
		if (prevNode == null) { // the method throws a nullpointerException when trying to catch the first
								// person in the seeking Ring, but i cannot figure out how to solve it without
								// messing up the rest of the code.
			prevNode = currentNode;
			while (prevNode.next != null) {
				prevNode = prevNode.next;
			}
//			seekerRingFront = currentNode.next;
		}

		if (currentNode == seekerRingFront) {
			seekerRingFront = currentNode.next;
		} else {
			prevNode.next = currentNode.next;
		}

		currentNode.next = capturedListFront;
		capturedListFront = currentNode;
		currentNode.capturedBy = prevNode.name;

	}

	// The SeekerNode class is used to store the information for one
	// player in the game of seeker. Initially the "capturedBy" field
	// is set to null, but when the person is captured, this should be
	// set to the name of the captor.

	private static class SeekerNode {
		private String name; // this person's name
		private String capturedBy; // name of who captured this person (null if
									// still in the seeking ring)
		private SeekerNode next; // next node in the list

		private SeekerNode(String name) {
			this(name, null);
		}

		private SeekerNode(String name, SeekerNode next) {
			this.name = name;
			this.capturedBy = null;
			this.next = next;
		}
	}

}
