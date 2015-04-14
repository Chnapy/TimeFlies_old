/*
 * 
 * 
 * 
 */
package gameplay.core;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;

/**
 * Timeline.java
 * Représente la timeline, qui affiche l'ensemble des entités actives du combat.
 * Gère également la boucle de jeu.
 *
 */
public class Timeline {

	private Array<EntiteActive> listEntiteActives;

	/**
	 *
	 * @param tabJoueurs
	 */
	public Timeline(Joueur[] tabJoueurs) {
		//TODO Récupérer les entités actives de chaque joueurs
	}

	/**
	 * Attribue aux entités actives une "initiative" permettant de définir
	 * l'ordre de jeu des entités actives (qui joue après qui) pendant le
	 * tour global à venir.
	 * Dépend du niveau symbolique des entités, mais également avec une légère
	 * part d'aléatoire.
	 * Voir GDD pour plus d'info.
	 */
	private void initInitiative() {
		//TODO
	}

	/**
	 * Applique l'ordre de jeu des entités actives d'après leur initiative.
	 */
	private void appliquerOrdreDeJeu() {
		//TODO
	}

}
