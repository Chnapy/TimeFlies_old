/*
 * 
 * 
 * 
 */
package gameplay.map;

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
	 * @param plan
	 */
	public Map(Etat[][] plan) {
		tabTuiles = new Tuile[plan.length][plan[0].length];
		init(plan);
	}

	private void init(Etat[][] plan) {
		for (int i = 0; i < plan.length; i++) {
			for (int j = 0; j < plan[0].length; j++) {
				tabTuiles[i][j] = new Tuile(plan[i][j], new Point(i, j));
			}
		}
	}

	public Tuile[][] getTabTuiles() {
		return tabTuiles;
	}

}
