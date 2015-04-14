/*
 * 
 * 
 * 
 */
package gameplay.map;

/**
 * Map.java
 * Représente la map du combat.
 *
 */
public class Map {

	private Tuile[][] tabTuiles;

	/**
	 *
	 * @param width
	 * @param height
	 */
	public Map(int width, int height) {
		tabTuiles = new Tuile[width][height];
	}

}
