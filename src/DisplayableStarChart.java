
/**
 * Name:WANIYA MEHDI 
 * Date: 12 dec 2023
 * CSC 202
 * Project 4-DisplayableStarChart.java 
 * 
 * An interface so that StarChart is guaranteed to have the methods
 * required by StarDisplay.
 */
import java.awt.Graphics;

public interface DisplayableStarChart {
	/**
	 * adds the star being passed into a data structure (Map, set)etc.
	 * 
	 * @param star- the object star
	 * @param name  - the name of the star
	 */
	public abstract void addStar(Star star, String name);

	/**
	 * adds the stars into a constellation
	 * 
	 * @param constellationName - the constellation name
	 * @param starNames         - the names of the stars in the constellation
	 */
	public abstract void addConstellation(String constellationName, String[] starNames);

	/**
	 * Checks if any constellation is displayed in the graphics window
	 * 
	 * @return true if any constellation is currently displayed in the graphics
	 *         window
	 */
	public abstract boolean isAnyConstellationDisplayed();

	/**
	 * Checks if a particular constellation is being displayed in the graphics
	 * window
	 * 
	 * @param name- name of the constellation that is to be displayed
	 * @return true if the constellation name received is currently displayed in the
	 *         graphics window.
	 */
	public abstract boolean isThisConstellationDisplayed(String name);

	/**
	 * Removes the constellation whose name is received from being displayed in the
	 * graphics window.
	 * 
	 * @param constellationName = name of the constellation that is to be removed
	 */
	public abstract void removeConstellation(String constellationName);

	/**
	 * accesses the name of the star in the star chart
	 * 
	 * @param star- the star whose name is to be accessed
	 * @return the name associated with a given star
	 */
	public abstract String getName(Star star);

	/**
	 * accesses the star based on the name in the star chart
	 * 
	 * @param name - name of the star that is to be accessed
	 * @return the star associated with a given star
	 */
	public abstract Star getStar(String name);

	/**
	 * records that the given star has exploded in a supernova. The explosion also
	 * destroys any star whose 3D distance is ≤ 0.25 from the given star. These
	 * "dead" stars are not removed from your star chart nor are they purged from
	 * all their internal data structures.
	 * 
	 * @param star- the star that is to be exploded
	 * @return the number of stars destroyed
	 */
	public abstract int supernova(Star star);

	/**
	 * draws all the stars and constellations present in the star chart
	 * 
	 * @param g            - the graphics window
	 * @param showStarName - if the star names are displayed or not
	 */
	public abstract void draw(Graphics g, boolean showStarName);
}
