/*
 * 
 * 
 * 
 */
package gameplay.core;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Carac;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import java.util.Observable;

/**
 * Timeline.java
 * Représente la timeline, qui gère l'ensemble des entités actives du combat.
 * Gère également la boucle de jeu.
 *
 */
public class Timeline extends Observable implements Runnable {

	//Thread possédant le runnable
	private final Thread thread;

	//Booleen définissant la poursuite ou non du combat
	private boolean enJeu;

	//Liste des entités actives du jeu
	private final Array<EntiteActive> listEntiteActives;

	//Entité active jouant actuellement son tour
	private EntiteActive entiteEnCours;

	//Etat du tour actuel
	private Tour etatTour;

	//Etat du tour global actuel
	private Tour etatTourGlobal;

	/**
	 *
	 * @param listPersonnages
	 */
	public Timeline(Array<Personnage> listPersonnages) {
		listEntiteActives = new Array<EntiteActive>(listPersonnages);
		thread = new Thread(this);
		etatTour = Tour.ATTENTE;
		etatTourGlobal = Tour.ATTENTE;
	}

	/**
	 * Défini l'initiative.
	 * Lance la timeline.
	 */
	public void start() {
		initInitiative();
		enJeu = true;
		thread.start();
	}

	/**
	 * Arrete la timeline.
	 */
	public void stop() {
		enJeu = false;
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
	@Override
	public void run() {
		while (enJeu) {	//TODO : Utiliser une variable définissant la fin du combat
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
		etatTourGlobal = Tour.COURS;
		for (EntiteActive entActive : listEntiteActives) {
			entiteEnCours = entActive;
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
		etatTourGlobal = Tour.DEBUT;
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
		etatTourGlobal = Tour.FIN;
		for (EntiteActive entActive : listEntiteActives) {
			entActive.finTourGlobal();
		}
		etatTourGlobal = Tour.ATTENTE;
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

		etatTour = Tour.COURS;
		entActive.jouerTour();

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
		etatTour = Tour.DEBUT;

		entActive.debutTour();

		//Notification de la vue
		setChanged();
		notifyObservers();
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
		etatTour = Tour.FIN;
		entActive.finTour();

		//Notification de la vue
		setChanged();
		notifyObservers();
		etatTour = Tour.ATTENTE;
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
	 * initiative = niveau symbolique + random(0,1)*facteur
	 *
	 * Pseudo-code :
	 *
	 * - Inititalisation de l'initiative de chaque personnage
	 *
	 */
	private void initInitiative() {
		// on initialise la variable initiative
		int initiative = -1;
		//la boucle pour initialiser les initiatives
		for (EntiteActive entiteActive : listEntiteActives) {
			//calcule de l'initative
			initiative = entiteActive.getNiveauSymbol().getNiveau() + ((int) (Math.random() * 5));
			//on set l'initiative des joueurs
			entiteActive.getCaracPhysique().setActu(Carac.INITIATIVE, initiative);
		}
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

		// ordonne les entitée active en fonction de leurs initiative
		triRapide(this.listEntiteActives);

		//Notification de la vue sur le nouvel ordre de jeu
		setChanged();
		notifyObservers();
	}

	/**
	 * Tri la liste d'entié Active en fonction de leurs initiative
	 *
	 * @param liste
	 */
	public static void triRapide(Array<EntiteActive> liste) {
		int longueur = liste.size;
		triRapide(liste, 0, longueur - 1);
	}

	/**
	 * Tri la liste d'entié Active en fonction de leurs initiative
	 * en partant du début jusqu'a la fin
	 *
	 * @param liste
	 * @param deb
	 * @param fin
	 */
	private static void triRapide(Array<EntiteActive> liste, int deb, int fin) {
		if (deb < fin) {
			int positionPivot = partition(liste, deb, fin);
			triRapide(liste, deb, positionPivot - 1);
			triRapide(liste, positionPivot + 1, fin);
		}
	}

	/**
	 *
	 * @param liste
	 * @param deb
	 * @param fin
	 * @return pivotPosition
	 */
	private static int partition(Array<EntiteActive> liste, int deb, int fin) {
		int compt = deb;
		EntiteActive pivot = liste.get(deb);

		for (int i = deb + 1; i <= fin; i++) {
			if (liste.get(i).getCaracPhysique().getCaracteristique(Carac.INITIATIVE).getActu() < pivot.getCaracPhysique().getCaracteristique(Carac.INITIATIVE).getActu()) {
				compt++;
				liste.swap(compt, i);
			}
		}
		liste.swap(deb, compt);
		return (compt);
	}

	/**
	 * Permet d'ajouter des entité actives à la liste, et donc de les utiliser.
	 *
	 * @param tabEntiteActive
	 */
	public void addEntiteActive(EntiteActive... tabEntiteActive) {
		listEntiteActives.addAll(tabEntiteActive);
	}

	public EntiteActive getEntiteEnCours() {
		return entiteEnCours;
	}

	public Tour getEtatTour() {
		return etatTour;
	}

	public Tour getEtatTourGlobal() {
		return etatTourGlobal;
	}

	public Array<EntiteActive> getListEntiteActives() {
		return listEntiteActives;
	}

}
