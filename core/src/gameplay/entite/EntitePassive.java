/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.Orientation;

/**
 * EntitePassive.java
 * Représente une entité passive (autonome).
 *
 */
public class EntitePassive extends Entite {

	public EntitePassive(String n,
			int posX, int posY, Orientation orient) {

		super(n, posX, posY, orient);
	}

}
