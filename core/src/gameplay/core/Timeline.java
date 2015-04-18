/*
 * 
 * 
 * 
 */
package gameplay.core;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;

/**
 * Timeline.java
 * Représente la timeline, qui gère l'ensemble des entités actives du combat.
 * Gère également la boucle de jeu.
 *
 */
public class Timeline {

	private Array<EntiteActive> listEntiteActives;

	/**
	 *
	 * @param listPersonnages
	 */
	public Timeline(Array<Personnage> listPersonnages) {
		listEntiteActives = new Array(listPersonnages);
	}

	/**
	 * Lance la timeline (on devrait en faire un thread ?).
	 */
	public void start() {
		initInitiative();
		boucleDeJeu();
	}

	/**
	 * Représente la boucle de jeu
	 * Elle s'arrête en cas de victoire (ou de défaite).
	 *
	 * Pseudo-code :
	 *
	 * - Placement des personnages sur la map
	 * - Inititalisation de l'initiative de chaque personnage
	 * 
	 * - Lancement d'un tour global :
	 * -- Ordonner la liste des entités actives en fonction de leur initiative
	 * -- Pour chaque entité active :
	 * --- Pour chacun de leur envoûtement, lancer l'action de début de tour
	 * global
	 * 
	 * -- Pour chaque entité active, si le combat peut continuer, lancement du
	 * tour de l'entité :
	 * --- Pour chacun de ses envoûtements, lancer l'action de début de tour
	 * --- Le joueur joue son tour
	 * --- Pour chacun de ses envoûtements, lancer l'action de fin de tour
	 * 
	 * -- Pour chaque entité active :
	 * --- Pour chacun de leur envoûtement, lancer l'action de fin de tour
	 * global
	 * 
	 * - Si le combat peut continuer, lancement d'un tour global.
	 *
	 */
	public void boucleDeJeu() {
		while (true) {	//TODO : Utiliser une variable définissant la fin du combat
			tourGlobal();
		}
	}

	/**
	 * Un tour global représente un tour de jeu où l'ensemble des entité actives
	 * joue l'une après l'autre. Une fois que l'ensemble de ces entité à joué
	 * son tour, le tour global se termine, et un autre tour global se lance.
	 *
	 * Pseudo-code :
	 *
	 * - Ordonner la liste des entités actives en fonction de leur initiative
	 * - Pour chaque entité active :
	 * -- Pour chacun de leur envoûtement, lancer l'action de début de tour
	 * global
	 * 
	 * - Pour chaque entité active, si le combat peut continuer, lancement du
	 * tour de l'entité :
	 * -- Pour chacun de ses envoûtements, lancer l'action de début de tour
	 * -- Le joueur joue son tour
	 * -- Pour chacun de ses envoûtements, lancer l'action de fin de tour
	 * 
	 * - Pour chaque entité active :
	 * -- Pour chacun de leur envoûtement, lancer l'action de fin de tour global
	 */
	public void tourGlobal() {
		//Début du tour global
		debutTourGlobal();

		//Milieu du tour global
		for (EntiteActive entActive : listEntiteActives) {
			tour(entActive);
		}

		//Fin du tour global
		finTourGlobal();
	}

	/**
	 * Actions lancées pour une entité active au moment où le tour global
	 * commence.
	 *
	 * Pseudo-code :
	 *
	 * - Pour chaque entité active :
	 * -- Pour chacun de leur envoûtement, lancer l'action de début de tour
	 * global
	 *
	 * @param entActive
	 */
	private void debutTourGlobal() {
		appliquerOrdreDeJeu();	//On définit l'ordre de jeu d'après l'initiative de chaque entité active
		for (EntiteActive entActive : listEntiteActives) {
			entActive.debutTourGlobal();
		}
	}

	/**
	 * Actions lancées pour une entité active au moment où le tour global
	 * commence.
	 * 
	 * Pseudo-code :
	 * 
	 * - Pour chaque entité active :
	 * -- Pour chacun de leur envoûtement, lancer l'action de fin de tour global
	 *
	 * @param entActive
	 */
	private void finTourGlobal() {
		for (EntiteActive entActive : listEntiteActives) {
			entActive.finTourGlobal();
		}
	}

	/**
	 * Un tour (non-global) représente un temps de jeu où une entité peut jouer.
	 * 
	 * Pseudo-code :
	 * 
	 * - Pour chacun de ses envoûtements, lancer l'action de début de tour
	 * - Le joueur joue son tour
	 * - Pour chacun de ses envoûtements, lancer l'action de fin de tour
	 *
	 * @param entActive
	 */
	public void tour(EntiteActive entActive) {
		debutTour(entActive);

		//TODO : Actions du tour, là où le joueur joue
		finTour(entActive);
	}

	/**
	 * Actions lancées pour une entité active au moment où le tour de cette
	 * entité commence.
	 * 
	 * Pseudo-code :
	 * 
	 * - Pour chacun de ses envoûtements, lancer l'action de début de tour
	 *
	 * @param entActive
	 */
	private void debutTour(EntiteActive entActive) {
		entActive.debutTour();
	}

	/**
	 * Actions lancées pour une entité active au moment où le tour de cette
	 * entité finit.
	 * 
	 * Pseudo-code :
	 * 
	 * - Pour chacun de ses envoûtements, lancer l'action de fin de tour
	 *
	 * @param entActive
	 */
	private void finTour(EntiteActive entActive) {
		entActive.finTour();
	}

	/**
	 * Attribue aux entités actives une "initiative" permettant de définir
	 * l'ordre de jeu des entités actives (qui joue après qui) pendant le
	 * tour global à venir.
	 * Dépend du niveau symbolique des entités, mais également avec une
	 * légère
	 * part d'aléatoire.
	 * Voir GDD pour plus d'info.
	 * 
	 * Pseudo-code :
	 * 
	 * - Inititalisation de l'initiative de chaque personnage
	 * 
	 */
	private void initInitiative() {
		//TODO
	}

	/**
	 * Applique l'ordre de jeu des entités actives d'après leur initiative.
	 * 
	 * Pseudo-code :
	 * 
	 * - Ordonner la liste des entités actives en fonction de leur initiative
	 * 
	 */
	private void appliquerOrdreDeJeu() {
		//TODO
	}

	/**
	 * Permet d'ajouter des entité actives à la liste, et donc de les utiliser.
	 *
	 * @param tabEntiteActive
	 */
	public void addEntiteActive(EntiteActive... tabEntiteActive) {
		listEntiteActives.addAll(tabEntiteActive);
	}

}
