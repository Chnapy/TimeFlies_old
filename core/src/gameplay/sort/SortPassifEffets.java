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

	//Liste des déclenchables demandés pour lancer les effets
	protected Array<Declenchable> listDeclenchables;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param declenchables
	 * @param index
	 *
	 */
	public SortPassifEffets(String nom, String description, Niveau niveau,
			Effet[] effets, Array<Declenchable> declenchables,
			int index) {
		super(nom, description, niveau, effets, index);
		listDeclenchables = declenchables;
	}

	/**
	 * Applique les effet du sort passif sur la cible
	 *
	 * @param effets
	 * @param lanceur
	 * @param cible
	 * @param isAvant
	 * @param ccritique
	 */
	public abstract void applyEffect(Effet[] effets, Entite lanceur, Entite cible, boolean isAvant, boolean ccritique);

}
