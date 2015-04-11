/*
 * 
 * 
 * 
 */
package gameplay.core;

import gameplay.map.Map;

/**
 * Combat.java
 * Représente un combat entre plusieurs joueurs, dans une map.
 *
 */
public class Combat {

	private Map map;
	private Timeline timeline;
	private Joueur[] tabJoueurs;

	public Combat(Map m, Joueur[] joueurs) {
		map = m;
		tabJoueurs = joueurs;
		timeline = new Timeline(joueurs);
	}

}
