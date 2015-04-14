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
	private Personnage[] tabPersonnages;

	/**
	 *
	 * @param _id
	 * @param pseud
	 * @param personnages
	 */
	public Joueur(int _id, String pseud, Personnage[] personnages) {
		id = _id;
		pseudo = pseud;
		tabPersonnages = personnages;
	}

}
