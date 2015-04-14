/*
 * 
 * 
 * 
 */
package gameplay.map;

import java.awt.Point;

/**
 * Tuile.java
 * Représente une tuile (une case) de la map.
 *
 */
public class Tuile {

	private Etat etat;
	private Point position;

	/**
	 *
	 * @param e
	 */
	public Tuile(Etat e) {
		etat = e;
	}

}
