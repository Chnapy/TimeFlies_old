/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Orientation;
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

	private Orientation oriAttaque;

	private Orientation precOriAttaque;

	private boolean critique;

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
		Point pt = new Point(x, y);

		if (entiteEnCours.getEtat() == EtatEntite.DEPLACEMENT) {
			if (path != null) {
				deplacer(x, y);
				path = null;
			}
		} else if (entiteEnCours.getEtat() == EtatEntite.SORT) {
			//Lancement de sort sur toute la zone action
			if (vue.getVmap().getTabVtuiles()[y][x].getEtat() == EtatTuile.ZONESORT) {
				if (entiteEnCours.tempsDispo() >= sortEnCours.getTempsAction()) {
					precOriAttaque = oriAttaque;
					oriAttaque = getOrientation(entiteEnCours.getCaracSpatiale().getPosition(), pt);
//					System.out.println(oriAttaque);
					critique = isCoupCritique(entiteEnCours.getCaracSpatiale().getOrientation(), oriAttaque);
					if (oriAttaque != precOriAttaque) {
						addAction(new Action(pt, entiteEnCours.getOrienter(), oriAttaque, precOriAttaque, critique));
					}
					addAction(new Action(pt, sortEnCours, oriAttaque, precOriAttaque, critique));
					entiteEnCours.subTime(sortEnCours.getTempsAction());
				}
			}
			modeDeplacement();
		}
	}

	private void deplacer(int x, int y) {
		for (int i = 0; i < path.size; i++) {
			precOriAttaque = oriAttaque;
			if (i == 0) {
				oriAttaque = getOrientation(entiteEnCours.getCaracSpatiale().getPosition(), path.get(i));
			} else {
				oriAttaque = getOrientation(path.get(i - 1), path.get(i));
			}
//					System.out.println(oriAttaque);
			critique = isCoupCritique(entiteEnCours.getCaracSpatiale().getOrientation(), oriAttaque);
			if (oriAttaque != precOriAttaque) {
				addAction(new Action(path.get(i), entiteEnCours.getOrienter(), oriAttaque, precOriAttaque, critique));
			}
			addAction(new Action(path.get(i), entiteEnCours.getDeplacer(), oriAttaque, precOriAttaque, critique));
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
			if (arg instanceof Action) {
				Action action = (Action) arg;

				if (action.getEtat() == Action.EtatAction.DEPLACEMENT) {
					entite.setEnDeplacement(true);
					action.getSort().lancerSort(entite, map.getTabTuiles()[action.getPoint().x][action.getPoint().y], entite, action.getOriAttaque(), action.isCritique());

				} else if (action.getEtat() == Action.EtatAction.SORT) {
					Tuile[] tuilesTouchees = map.getTuilesAction(action.getSort().getZoneAction().getZoneFinale(), new Point(action.getPoint().x, action.getPoint().y));
					for (Tuile t : tuilesTouchees) {
						lancerSort(entite, action.getSort(), t, action.getOriAttaque(), action.isCritique());
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
	 * @param oriAttaque
	 * @param critique
	 */
	public void lancerSort(Entite lanceur, SortActif sort, Tuile tuileCible, Orientation oriAttaque, boolean critique) {
		Personnage persoCible = getPerso(tuileCible);
		tuileCible.recoitSort(sort.getTabEffets(), lanceur, oriAttaque, critique);
		if (persoCible != null) {
			System.out.println("ok");
			persoCible.recoitSort(sort.getTabEffets(), lanceur, oriAttaque, critique);
		}
	}

	/**
	 * Retourne le personnage présent sur la tuile.
	 *
	 * @param tuile
	 * @return le personnage présent sur la tuile. null si vide
	 */
	public Personnage getPerso(Tuile tuile) {
		for (Joueur tabJoueur : tabJoueurs) {
			for (Personnage perso : tabJoueur.getPersonnages()) {
				if (perso.getCaracSpatiale().getPosition().equals(tuile.getPosition())) {
					return perso;
				}
			}
		}
		return null;
	}

	/**
	 * Récupère l'orientation de l'entité par rapport à un point donné.
	 *
	 * @param origine
	 * @param point
	 * @return l'orientation de l'origine qui regarde vers le point
	 */
	private Orientation getOrientation(Point origine, Point point) {
		if (origine.equals(point)) {
			return null;
		}

		double vecX = point.getX() - origine.getX();
		double vecY = point.getY() - origine.getY();
//		System.out.println(vecX + " " + vecY);
		if (vecX > 0) {	//Est
			if (vecY > 0) {	//Sud
				if (vecX < vecY) {
					return Orientation.S;
				} else {
					return Orientation.E;
				}
			} else {	//Nord
				if (vecX < vecY) {
					return Orientation.N;
				} else {
					return Orientation.E;
				}
			}
		} else {	//Ouest
			if (vecY > 0) {	//Sud
				if (vecX < vecY) {
					return Orientation.S;
				} else {
					return Orientation.O;
				}
			} else {	//Nord
				if (vecX > vecY) {
					return Orientation.N;
				} else {
					return Orientation.O;
				}
			}
		}
	}

	private boolean isCoupCritique(Orientation oriCible, Orientation oriAttaque) {
		if (oriAttaque == null || oriCible == null || oriAttaque.equals(oriCible)) {
			return false;
		}
		return oriAttaque.invert().equals(oriCible);
	}
}
