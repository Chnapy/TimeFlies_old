/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiqueSpatiale;
import gameplay.caracteristique.Orientation;

/**
 * Entite.java
 * Représente une entité, visible en combat.
 *
 */
public abstract class Entite {

	protected final String nom;
	protected CaracteristiqueSpatiale caracSpatiale;

	public Entite(String n, int posX, int posY, Orientation orient) {
		nom = n;
		caracSpatiale = new CaracteristiqueSpatiale(posX, posY, orient);
	}

}
