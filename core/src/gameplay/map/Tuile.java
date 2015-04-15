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
	
	private boolean occupe;	//La tuile est occupée par une entité

	/**
	 *
	 * @param e
	 */
	public Tuile(Etat e, Point pos) {
		etat = e;
		position = pos;
	}

}
