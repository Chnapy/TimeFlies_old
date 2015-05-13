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

	//Nom du sort
	private String nom;

	//Description
	private String description;

	//Niveau et expérience
	private Niveau niveau;

	//Tableau des effets
	private Effet[] tabEffets;

	//Index pour la vue
	private final int index;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param i	          pour la vue
	 */
	public Sort(String nom, String description, Niveau niveau, Effet[] effets, int i) {
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
		tabEffets = effets;
		index = i;
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

	public String getDescription() {
		return description;
	}

	public int getIndex() {
		return index;
	}

}
