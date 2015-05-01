/*
 * 
 * 
 * 
 */
package gameplay.sort;

import com.badlogic.gdx.utils.Array;
import gameplay.effet.Declenchable;
import gameplay.effet.Effet;
import gameplay.entite.Entite;

/**
 * SortPassifEffets.java
 * Représente un sort passif pouvant être déclenché.
 *
 */
public abstract class SortPassifEffets extends SortPassif {

	protected Array<Declenchable> listDeclenchables;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param declenchables
	 * @param index
	 * @param isAvant         true si le passif se déclanche avant le sort
	 * @param canActivePassif true si le passif peu activer d'autre passif
	 * @param isCumulable     true si le sort de déclanchera a chaque effet
	 *                        produit ou false si il se déclanche une seul fois
	 */
	public SortPassifEffets(String nom, String description, Niveau niveau,
			Effet[] effets, Array<Declenchable> declenchables,
			int index) {
		super(nom, description, niveau, effets, index);
		listDeclenchables = declenchables;
	}

	/**
	 * applique les effet du sort passif sur la victime
	 *
	 * @param effets
	 * @param lanceur
	 * @param victime
	 * @param isAvant
	 */
	public abstract void applyEffect(Effet[] effets, Entite lanceur, Entite victime, boolean isAvant);

}
