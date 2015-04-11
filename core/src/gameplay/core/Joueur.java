/*
 * 
 * 
 * 
 */
package gameplay.core;

import gameplay.entite.EntiteActive;

/**
 * Joueur.java
 * Représente un joueur.
 *
 */
public class Joueur {

	private final int id;

	private String pseudo;
	private EntiteActive[] tabEntites;

	public Joueur(int _id, String pseud, EntiteActive[] entites) {
		id = _id;
		pseudo = pseud;
		tabEntites = entites;
	}

}
