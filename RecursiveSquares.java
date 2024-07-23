/**
 * Name(s):  Solyana & Waniya
 * CSC 202
 * Lab10-RecursiveSquares.java
 * 
 * Program to draw pattern of recursive square. The use enters the order of the 
 * recursive squares to be drawn. The pattern is drawn centered in a square
 * window.
 */
import java.awt.Graphics; 
import java.awt.Color;
import javax.swing.JOptionPane;

public class RecursiveSquares {
	public static final int SIZE = 600;
	public static final int MAX_LEVEL = 5;

	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(SIZE, SIZE);
		Graphics g = panel.getGraphics();

		int halfSize = SIZE / 2;
		int x = halfSize;
		int y = halfSize;
		int length = halfSize;

		int level = -1;
		do {
			try {
				String reply = JOptionPane.showInputDialog("Enter level of Recursive Squares");
				level = Integer.parseInt(reply);
				if (level < 0 || level > MAX_LEVEL) {
					JOptionPane.showMessageDialog(null,
							"The level must be at least 0 " + "and less than or equal to " + MAX_LEVEL);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter an integer!");
			}
		} while (level < 0 || level > MAX_LEVEL);

		drawRecursiveSquares(level, g, x, y, length);
	}

	/**
	 * Draws recursive squares centered at (centerX, centerY) with sides of the given length
	 * 
	 * @param level    - level of the recursive squares with level 0 being one centered square
	 * @param g        - facilitates drawing in the DrawingPanel
	 * @param centerX  - the x-coordinate of the center of the recursive squares pattern
	 * @param centerY  - the y-coordinate of the center of the recursive squares pattern
	 * @param sideLength - length of the side of the square
	 * 
	 */
	public static void drawRecursiveSquares(int level, Graphics g, int centerX, int centerY, int sideLength) {
		if(level == 0) {
			drawSquare( g, centerX,  centerY,  sideLength);
		} else {
			int leftX = centerX - (sideLength / 2);
			int topY = centerY - (sideLength / 2);
			int rightX = centerX + (sideLength / 2);
			int bottomY = centerY + (sideLength / 2);
			//drawSquare( g, centerX,  centerY,  sideLength);
			drawRecursiveSquares(level-1, g, leftX, topY, sideLength/2);
			drawRecursiveSquares(level-1, g, rightX, topY, sideLength/2);
			drawSquare( g, centerX,  centerY,  sideLength);		
			drawRecursiveSquares(level-1, g, leftX, bottomY, sideLength/2);
			drawRecursiveSquares(level-1, g, rightX, bottomY, sideLength/2);
		}
		
	}

	/**
	 * Draws one light gray filled square with a black outline
	 * @param g        - facilitates drawing in the DrawingPanel
	 * @param centerX  - the x-coordinate of the center of the square
	 * @param centerY  - the y-coordinate of the center of the square
	 * @param sideLength - length of the side of the square
	 */
	public static void drawSquare(Graphics g, int centerX, int centerY, int sideLength) {
		g.setColor(Color.LIGHT_GRAY);
		int x1 = centerX - (sideLength/2);
		int y1 = centerY - (sideLength/2);
		g.fillRect(x1, y1, sideLength, sideLength);
		g.setColor(Color.BLACK);
		g.drawRect(x1, y1, sideLength, sideLength);
	}

	/**
	 * A "pause" function for use in slowing down drawing (so that you can see the
	 * growth). This method shows the current drawing after the pause ends.
	 * 
	 * @param milliseconds The number of milliseconds to wait
	 */
	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// ignoring exception at the moment
		}
	}
}
