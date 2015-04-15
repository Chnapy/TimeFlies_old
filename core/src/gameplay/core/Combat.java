/*
 * 
 * 
 * 
 */
package gameplay.core;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;
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

	/**
	 *
	 * @param m
	 * @param joueurs
	 */
	public Combat(Map m, Joueur[] joueurs) {
		tabJoueurs = joueurs;
		map = m;
		EntiteActive[] tabPersonnages = getPersonnages(joueurs);
		timeline = new Timeline(tabPersonnages);
	}

	/**
	 * QUE LE COMBAT COMMENCE !
	 * On peut pas être plus explicite.
	 */
	public void lancer() {
		timeline.start();
	}

	/**
	 * Récupère les personnages de chaque tous les joueurs.
	 *
	 * @param joueurs
	 * @return
	 */
	private EntiteActive[] getPersonnages(Joueur[] joueurs) {
		Array<EntiteActive> listEntActive = new Array<EntiteActive>();

		for (Joueur j : joueurs) {
			listEntActive.addAll((EntiteActive[]) j.getPersonnages());
		}

		return listEntActive.toArray();
	}

}
