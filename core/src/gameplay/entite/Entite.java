/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiqueSpatiale;
import gameplay.caracteristique.Orientation;
import gameplay.sort.SortPassif;
import java.util.Observable;

/**
 * Entite.java
 * Représente une entité, visible en combat.
 *
 */
public abstract class Entite extends Observable {

	/**
	 *
	 */
	protected final String nom;

	/**
	 *
	 */
	protected CaracteristiqueSpatiale caracSpatiale;

	/**
	 * Que l'entité soit active ou passive, elle peut posséder des sorts passifs
	 */
	protected SortPassif[] tabSortPassif;

	/**
	 *
	 * @param n
	 * @param posX
	 * @param posY
	 * @param orient
	 * @param sortsPassifs
	 */
	public Entite(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs) {
		nom = n;
		caracSpatiale = new CaracteristiqueSpatiale(posX, posY, orient);
		tabSortPassif = sortsPassifs;
	}

	public String getNom() {
		return nom;
	}

}
