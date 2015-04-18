/*
 * 
 * 
 * 
 */
package gameplay.core;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.Personnage;
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
		Array<Personnage> listPersonnages = getPersonnages(joueurs);
		timeline = new Timeline(listPersonnages);
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
	private static Array<Personnage> getPersonnages(Joueur[] joueurs) {
		Array<Personnage> listEntActive = new Array<Personnage>();
		for (Joueur j : joueurs) {
			listEntActive.addAll(j.getPersonnages());
		}
		return listEntActive;
	}

}
