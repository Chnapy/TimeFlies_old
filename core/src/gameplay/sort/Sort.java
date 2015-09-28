/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import general.Orientation;
import java.util.Observable;

/**
 * Sort.java
 * Représente un sort, actif ou passif.
 *
 */
public abstract class Sort extends Observable {

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

	/**
	 * lance le sort sur la victime check ses passif et
	 * renvoi les effets des passifs au lanceur si effectif
	 *
	 * @param cibleEntite
	 * @param cibleTuile
	 * @param lanceur
	 * @param oriAttaque
	 * @param critique
	 */
	public void lancerSort(Entite cibleEntite, Tuile cibleTuile, EntiteActive lanceur, Orientation oriAttaque, boolean critique) {
		cibleEntite.recoitSort(getTabEffets(), lanceur, oriAttaque, critique);
		cibleTuile.recoitSort(getTabEffets(), lanceur, oriAttaque, critique);
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
