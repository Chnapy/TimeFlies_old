/*
 * 
 * 
 * 
 */
package gameplay.sort;

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
	private Effet[] tabEffets;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 */
	public Sort(String nom, String description, Niveau niveau, Effet[] effets) {
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
		tabEffets = effets;
	}

	public String getNom() {
		return nom;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public Effet[] getTabEffets() {
		return tabEffets;
	}

}
