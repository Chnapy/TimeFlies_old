/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.utils.Array;
import gameplay.core.Joueur;
import gameplay.core.Timeline;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.entite.EtatEntite;
import gameplay.entite.Personnage;
import gameplay.map.EtatTuile;
import gameplay.map.Map;
import gameplay.map.Tuile;
import gameplay.map.Type;
import gameplay.sort.SortActif;
import gameplay.sort.pileaction.Action;
import gameplay.sort.pileaction.ActionDeplacement;
import gameplay.sort.pileaction.ActionLancerSort;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import vue.vCombat;

/**
 * cCombat.java
 * Controleur gérant le combat.
 *
 * Pseudo-code :
 *
 * - Récupération des joueurs (SERVEUR)
 * - Récupération de la map (SERVEUR)
 * - Placement aléatoire des personnages (SERVEUR)
 * - Affichage du tout (CLIENT)
 * - Placement des personnages par les joueurs (CLIENT)
 * - Envoi du placement au serveur (CLIENT)
 * - Lancement du combat (CLIENT-SERVEUR)
 *
 */
public class cCombat implements Observer {

	//Vue
	private final vCombat vue;

	//Map (modele)
	private final Map map;

	//Tableau des joueurs
	private final Joueur[] tabJoueurs;

	//Timeline (modele)
	private final Timeline timeline;

	//Chemin (liste de points) de l'entité active à la tuile ciblée
	private Array<Point> path;

	//Entité jouant son tour
	private EntiteActive entiteEnCours;

	//Sort sélectionné par le joueur
	private SortActif sortEnCours;

	public cCombat(Map m, Joueur[] joueurs) {
		map = m;
		tabJoueurs = joueurs;
		Array<Personnage> listPersonnages = getPersonnages(joueurs);
		timeline = new Timeline(listPersonnages);
		vue = new vCombat(this, m.getTabTuiles(), listPersonnages, timeline);
		timeline.addObserver(this);

		listPersonnages.forEach((perso) -> {
			perso.addObserver(this);

			//La position de chaque joueur devient occupée
			map.setTuileOccupe(true, perso.getCaracSpatiale().getPosition().y, perso.getCaracSpatiale().getPosition().x);
		});
	}

	/**
	 * Lance le combat.
	 */
	public void lancer() {
		timeline.start();
	}

	/**
	 * Arrete le combat.
	 */
	public void stop() {
		timeline.stop();
	}

	//Lancé lorsqu'un nouveau tour d'une entité commence
	public void nouveauTour() {
		path = null;
		map.setTuileOccupe(false, entiteEnCours.getCaracSpatiale().getPosition().y, entiteEnCours.getCaracSpatiale().getPosition().x);
		vue.nouveauTour(this, entiteEnCours);
	}

	//Lancé lorsqu'un tour d'une entité finit
	public void finTour() {
		vue.finTour();
		map.setTuileOccupe(true, entiteEnCours.getCaracSpatiale().getPosition().y, entiteEnCours.getCaracSpatiale().getPosition().x);
	}

	public void tourEnCours() {
		vue.tourEnCours(entiteEnCours);
	}

	/**
	 * Récupère les personnages de tous les joueurs.
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

	/**
	 * Lancé lors du survol d'une tuile.
	 * Calcul le nouveau chemin depuis l'entité active du tour.
	 *
	 * @param x
	 * @param y
	 */
	public void survolTuile(int x, int y) {
		Tuile tuile = map.getTabTuiles()[y][x];

		if (entiteEnCours.getEtat() == EtatEntite.DEPLACEMENT) {

			vue.clearColorTuile();
			//Déplacement
			Point depart = entiteEnCours.getLastPosition();
			if (!depart.equals(tuile.getPosition())
					&& !tuile.getType().equals(Type.OBSTACLE)
					&& !tuile.getType().equals(Type.TROU)
					&& !tuile.isOccupe()) {
				path = map.getChemin(depart, new Point(x, y));
				if (path != null) {
					vue.colorTuile(path);
				}
			} else {
				path = null;
			}
		} else if (entiteEnCours.getEtat() == EtatEntite.SORT) {
			//Afficher zone action

			if (vue.getVmap().getTabVtuiles()[y][x].getEtat() == EtatTuile.ZONESORT) {
				vue.afficherAction(sortEnCours.getZoneAction().getZoneFinale(), new Point(x, y));
			} else {
				vue.clearActionTuile();
			}
		}
	}

	/**
	 * Lors d'un clic sur une tuile, l'envoie depuis vMap.
	 * Fait déplacer l'entité active.
	 *
	 * @param x
	 * @param y
	 */
	public void clicSurTuile(int x, int y) {
		Tuile tuile = map.getTabTuiles()[y][x];
//		System.out.println(tuile.getEtat());

		if (entiteEnCours.getEtat() == EtatEntite.DEPLACEMENT) {
			if (path != null) {
				addAction(new ActionDeplacement(new Point(x, y), new Array<Point>(path)));
				path = null;
			}
		} else if (entiteEnCours.getEtat() == EtatEntite.SORT) {
			//Lancement de sort sur toute la zone action
			if (vue.getVmap().getTabVtuiles()[y][x].getEtat() == EtatTuile.ZONESORT) {
				if(entiteEnCours.tempsDispo()>=sortEnCours.getTempsAction()){
					addAction(new ActionLancerSort(new Point(x, y), sortEnCours));
					entiteEnCours.subTime(sortEnCours.getTempsAction());
				}
			}
			modeDeplacement();
		}
	}

	/**
	 * Passage en mode sort
	 * Affichage de la zone de portée
	 *
	 * @param index
	 */
	public void modeSort(int index) {
		entiteEnCours.setEtat(EtatEntite.SORT);
		sortEnCours = entiteEnCours.setSortEnCours(index);
		Point depart = entiteEnCours.getLastPosition();
		vue.afficherPortee(sortEnCours.getZonePortee().getZoneFinale(), depart);
	}

	/**
	 * Passage en mode déplacement
	 *
	 */
	public void modeDeplacement() {
		entiteEnCours.setEtat(EtatEntite.DEPLACEMENT);
		sortEnCours = null;
		vue.clearAll();
	}

	private void addAction(Action action) {
		entiteEnCours.addAction(action);
		vue.addAction(action);
	}

	/**
	 * Lance les nouveaux tours, les fins tours
	 *
	 * Lance les actions sorts/déplacements
	 *
	 * @param o	  Timeline EntiteActive
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Timeline) {
			Timeline tl = (Timeline) o;
			switch (tl.getEtatTour()) {
				case COURS:
					tourEnCours();
					break;
				case DEBUT:
					entiteEnCours = tl.getEntiteEnCours();
					nouveauTour();
					break;
				case FIN:
					finTour();
					break;
			}
		} else if (o instanceof EntiteActive) {
			EntiteActive entite = (EntiteActive) o;
			if (arg instanceof ActionDeplacement) {
				ActionDeplacement action = (ActionDeplacement) arg;
				entite.setEnDeplacement(true);
				entite.setPosition(action.getPath());
			} else if (arg instanceof ActionLancerSort) {
				ActionLancerSort action = (ActionLancerSort) arg;
				if (vue.getVmap().getTabVtuiles()[action.getPoint().y][action.getPoint().x].getEtat() == EtatTuile.ZONESORT) {
					Tuile[] tuilesTouchees = map.getTuilesAction(action.getSort().getZoneAction().getZoneFinale(), new Point(action.getPoint().x, action.getPoint().y));
					for (Tuile t : tuilesTouchees) {
						t.recoitSort(action.getSort().getTabEffets(), entite);
					}
				}
			}
		}
	}

	public vCombat getVue() {
		return vue;
	}

	/**
	 * Permet de lancer un sort sur la tuile tuileCible
	 *
	 * @param lanceur
	 * @param sort
	 * @param tuileCible
	 */
	public void lancerSort(Entite lanceur, SortActif sort, Tuile tuileCible) {
		Personnage persoCible = getPerso(tuileCible);
		if (persoCible == null) {
			tuileCible.recoitSort(sort.getTabEffets(), lanceur);
		} else {
			persoCible.recoitSort(sort.getTabEffets(), lanceur);
		}
	}

	/**
	 * Retourne le personnage présent sur la tuile.
	 *
	 * @param tuile
	 * @return le personnage présent sur la tuile. null si vide
	 */
	public Personnage getPerso(Tuile tuile) {
		Personnage[] persos = null;
		for (int i = 0; i < tabJoueurs.length; i++) {
			persos = tabJoueurs[i].getPersonnages();
			for (int k = 0; k < persos.length; k++) {
				if (persos[k].getCaracSpatiale().getPosition().equals(tuile.getPosition())) {
					return persos[k];
				}
			}
		}
		return null;
	}
}
