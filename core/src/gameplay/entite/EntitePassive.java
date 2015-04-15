/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.Orientation;
import gameplay.sort.SortPassif;

/**
 * EntitePassive.java
 * Représente une entité passive (autonome).
 *
 */
public abstract class EntitePassive extends Entite {

	/**
	 *
	 * @param n
	 * @param posX
	 * @param posY
	 * @param orient
	 * @param sortsPassifs
	 */
	public EntitePassive(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs) {

		super(n, posX, posY, orient, sortsPassifs);
	}

}
