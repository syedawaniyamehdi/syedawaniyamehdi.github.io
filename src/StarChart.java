
/**
 * Name:Waniya Mehdi 
 * Date: 12th dec 2023
 * CSC 202
 * Project 4-StarChart.java
 * 
 * This class represents information about stars to be displayed including
 * the position of each star, names of stars, and constellations. The
 * distance between two stars can be determined. A supernova can be 
 * simulated creating dead stars.
 * 
 * Document Assistance(who and describe; if no assistance, declare that fact):
 * Prof.Mueller also helped me with the superNoa method strategy.
 * prof.Mueller helped me with strategizing and solving the write name private method for the draw method
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class StarChart implements DisplayableStarChart {
	private static double SUPERNOVA_DISTANCE = 0.25;

	private int width;
	private int height;
	private Map<Star, String> starNameMap;
	private Map<String, String[]> constellationMap;
	private Map<String, Star> getStarMap;
	private Set<Star> deadStars;

	/**
	 * Initializes a new star chart window , based off of the given width and height
	 * 
	 * @param width  - width of the window
	 * @param height - height of the window
	 */
	public StarChart(int width, int height) {
		this.width = width;
		this.height = height;
		this.starNameMap = new HashMap<>();
		this.constellationMap = new HashMap<>();
		this.getStarMap = new HashMap<>();
		this.deadStars = new HashSet<>();
	}

	@Override
	public void addStar(Star star, String name) {
		starNameMap.put(star, name);
		getStarMap.put(name, star);

	}

	@Override
	public void addConstellation(String constellationName, String[] starNames) {
		constellationMap.put(constellationName, starNames);
	}

	@Override
	public String getName(Star star) {
		return starNameMap.get(star);
	}

	@Override
	public boolean isAnyConstellationDisplayed() {
		return !constellationMap.isEmpty();
	}

	@Override
	public boolean isThisConstellationDisplayed(String constellationName) {
		return constellationMap.containsKey(constellationName);
	}

	@Override
	public void removeConstellation(String constellationName) {
		constellationMap.remove(constellationName);
	}

	@Override
	public Star getStar(String name) {
		if (getStarMap.containsKey(name)) {
			return getStarMap.get(name);
		} else {
			return null;
		}
	}

	@Override
	public int supernova(Star star) {
		int numDestroyedStar = 0;
		if (deadStars.contains(star)) {
			return 0;
		} else {
			// deadStars.add(star);
			// numDestroyedStar++;
			for (Star s : starNameMap.keySet()) {
				if (star.distance(s) <= SUPERNOVA_DISTANCE && !deadStars.contains(s)) {
					deadStars.add(s);
					// deadStars.add(star);
					numDestroyedStar++;
				}
			}

		}
		return numDestroyedStar;

	}

	@Override
	public void draw(Graphics g, boolean showStarName) {
		for (Star s : starNameMap.keySet()) {
			drawStar(g, s);
		}
		Set<Star> drawnStars = new HashSet<>();
		for (String constellation : constellationMap.keySet()) {
			drawConstellation(g, constellationMap.get(constellation));
			writeNames(g, showStarName, constellation, drawnStars);
			// draw constellation name when needed
		}

	}

	// draws a star and checks what color it needs to be
	private void drawStar(Graphics g, Star star) {
		int starX = star.pixelX(width);
		int starY = star.pixelY(height);
		int starSize = star.pixelSize();
		g.setColor(star.pixelColor());
		if (deadStars.contains(star)) { // checks if the star is dead
			g.setColor(Color.RED);
		}

		g.fillRect(starX, starY, starSize, starSize);

	}

	// draw constellation lines
	private void drawConstellation(Graphics g, String[] stars) {
		for (int i = 0; i < stars.length; i = i + 2) {
			Star star1 = getStarMap.get(stars[i]);
			Star star2 = getStarMap.get(stars[i + 1]);
			g.setColor(Color.YELLOW);
			g.drawLine(star1.pixelX(width), star1.pixelY(height), star2.pixelX(width), star2.pixelY(height));
		}
	}

	// writes the names
	private void writeNames(Graphics g, boolean showStarName, String constellationName, Set<Star> drawnStars) {
		String[] starNames = constellationMap.get(constellationName);
		if (showStarName) {
			g.setColor(Color.WHITE);
			for (int i = 0; i < starNames.length; i++) {
				Star starToWrite = getStarMap.get(starNames[i]);
				if (!drawnStars.contains(starToWrite)) {
					g.drawString(starNames[i], starToWrite.pixelX(width), starToWrite.pixelY(height));
					drawnStars.add(starToWrite);
					// g.drawString(star1, width, height);
					// g.drawString(star2, width, height);
				}
			}
		} else {
			g.setColor(Color.YELLOW);
			int lastStar = starNames.length - 1;
			Star starToWrite = getStarMap.get(starNames[lastStar]);
			g.drawString(constellationName, starToWrite.pixelX(width), starToWrite.pixelY(height));
		}
	}

}
