/*
 * 
 * 
 * 
 */
package gameplay.core;

import gameplay.entite.Personnage;

/**
 * Joueur.java
 * Représente un joueur.
 *
 */
public class Joueur {

	//Id du joueur
	private final int id;

	//Pseudo
	private String pseudo;

	//Tableau des personnages
	private Personnage[] personnages;

	/**
	 *
	 * @param _id
	 * @param pseud
	 * @param personnages
	 */
	public Joueur(int _id, String pseud, Personnage[] personnages) {
		id = _id;
		pseudo = pseud;
		this.personnages = personnages;
	}

	public int getId() {
		return id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public Personnage[] getPersonnages() {
		return personnages;
	}

}
