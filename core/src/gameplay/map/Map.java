/*
 * 
 * 
 * 
 */
package gameplay.map;

import gameplay.entite.EntiteActive;
import java.awt.Point;

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

	public void init(Etat[][] plan) {
		for (int i = 0; i < plan.length; i++) {
			for (int j = 0; j < plan[0].length; j++) {
				tabTuiles[i][j] = new Tuile(plan[i][j], new Point(i, j));
			}
		}
	}

}
