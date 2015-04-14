/*
 * 
 * 
 * 
 */
package gameplay.sort;

import com.badlogic.gdx.utils.Array;
import gameplay.effet.Effet;

/**
 * Sort.java
 * Représente un sort, actif ou passif.
 *
 */
public abstract class Sort {

	private String nom;
	private String description;
	private Niveau niveau;
	private Array<Effet> listEffets;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 */
	public Sort(String nom, String description, Niveau niveau) {
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
		listEffets = new Array<Effet>();
	}

}
