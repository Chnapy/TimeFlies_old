/*
 * 
 * 
 * 
 */
package gameplay.sort;

import com.badlogic.gdx.utils.Array;

import gameplay.effet.Declenchable;
import gameplay.effet.Effet;

/**
 * SortPassifEffets.java
 * Représente un sort passif pouvant être déclenché.
 *
 */
public abstract class SortPassifEffets extends SortPassif {

	private Array<Declenchable> listDeclenchables;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param declenchables
	 * @param index
	 */
	public SortPassifEffets(String nom, String description, Niveau niveau,
			Effet[] effets, Array<Declenchable> declenchables,
			int index) {
		super(nom, description, niveau, effets, index);

		listDeclenchables = declenchables;
	}

}
