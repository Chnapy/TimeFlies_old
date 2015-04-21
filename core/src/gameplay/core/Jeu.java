/*
 * 
 * 
 * 
 */
package gameplay.core;

import com.badlogic.gdx.utils.Array;

/**
 * Jeu.java
 * Représente le jeu.
 * Contient l'ensemble des parties en cours.
 *
 */
public class Jeu {

	private Array<Combat> listCombat;

	/**
	 *
	 *
	 */
	public Jeu() {
		listCombat = new Array<Combat>();
	}

	/**
	 * Ajoute un combat au jeu.
	 *
	 * @param combat
	 */
	public void addCombat(Combat combat) {
		listCombat.add(combat);
	}

}
