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

	private final int id;

	private String pseudo;
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
