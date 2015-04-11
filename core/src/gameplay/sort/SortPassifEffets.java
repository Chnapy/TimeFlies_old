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
public class SortPassifEffets extends SortPassif {

	private Array<Effet> listEffets;
	private Array<Declenchable> listDeclenchables;

	public SortPassifEffets(String nom, String description, Niveau niveau,
			Array<Effet> effets, Array<Declenchable> declenchables) {

		super(nom, description, niveau);

		listEffets = effets;
		listDeclenchables = declenchables;
	}

}
