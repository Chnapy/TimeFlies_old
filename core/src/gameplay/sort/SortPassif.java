/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.effet.Effet;

/**
 * SortPassif.java
 * Représente un sort donnant un bonus permannent, ou pouvant être déclenché.
 *
 */
public abstract class SortPassif extends Sort {

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 */
	public SortPassif(String nom, String description, Niveau niveau, Effet[] effets) {

		super(nom, description, niveau, effets);

	}

}
