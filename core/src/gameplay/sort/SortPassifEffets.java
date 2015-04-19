/*
 * 
 * 
 * 
 */
package gameplay.sort;

import com.badlogic.gdx.utils.Array;
import gameplay.effet.Effet;
import gameplay.envoutement.Declenchable;

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
	 */
	public SortPassifEffets(String nom, String description, Niveau niveau,
			Effet[] effets, Array<Declenchable> declenchables) {
		super(nom, description, niveau, effets);

		listDeclenchables = declenchables;
	}

}
