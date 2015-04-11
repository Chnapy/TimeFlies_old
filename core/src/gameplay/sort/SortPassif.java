/*
 * 
 * 
 * 
 */
package gameplay.sort;

/**
 * SortPassif.java
 * Représente un sort donnant un bonus permannent, ou pouvant être déclenché.
 *
 */
public abstract class SortPassif extends Sort {

	public SortPassif(String nom, String description, Niveau niveau) {

		super(nom, description, niveau);

	}

}
