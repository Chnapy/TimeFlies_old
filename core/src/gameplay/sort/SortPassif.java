/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.effet.Effet;

/**
 * SortPassif.java
 * Représente un sort donnant un bonus permanent, ou pouvant être déclenché.
 *
 */
public abstract class SortPassif extends Sort {

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param index
	 */
	public SortPassif(String nom, String description, Niveau niveau, Effet[] effets, int index) {

		super(nom, description, niveau, effets, index);

	}

}
