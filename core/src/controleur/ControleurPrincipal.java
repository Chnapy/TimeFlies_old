/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Carac;
import gameplay.core.Joueur;
import gameplay.core.Timeline;
import gameplay.entite.EntiteActive;
import general.Mode;
import gameplay.entite.Personnage;
import gameplay.map.Map;
import gameplay.map.Tuile;
import gameplay.sort.pileaction.Action;
import general.Orientation;
import general.Tourable;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import vue.Vue;

/**
 * ControleurPrincipal.java ControleurPrincipal gérant le combat.
 *
 * Pseudo-code :
 *
 * - Récupération des joueurs (SERVEUR) - Récupération de la map (SERVEUR) -
 * Placement aléatoire des personnages (SERVEUR) - Affichage du tout (CLIENT) -
 * Placement des personnages par les joueurs (CLIENT) - Envoi du placement au
 * serveur (CLIENT) - Lancement du combat (CLIENT-SERVEUR)
 *
 */
public class ControleurPrincipal implements Observer, Tourable {

	//Controleur gérant les déplacements
	public final ControleurDeplacement controleurDeplacement;

	//Controleur gérant le lancement de sort
	public final ControleurSort controleurSort;

	//Vue
	private final Vue vue;

	//Map (modele)
	private final Map map;

	//Tableau des joueurs
	private final Joueur[] tabJoueurs;

	//Timeline (modele)
	private final Timeline timeline;

	//Entité jouant son tour
	private EntiteActive entiteEnCours;

	private Orientation oriAttaque;

	private Orientation precOriAttaque;

	public ControleurPrincipal(Map m, Joueur[] joueurs) {
		map = m;
		tabJoueurs = joueurs;
		Array<Personnage> listPersonnages = getPersonnages(joueurs);
		timeline = new Timeline(listPersonnages);
		timeline.addObserver(this);
		vue = new Vue(this, m.getTabTuiles(), listPersonnages, timeline);
		controleurDeplacement = new ControleurDeplacement(this, map, vue.getVmap());
		controleurSort = new ControleurSort(this, vue);

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

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		controleurDeplacement.nouveauTour(controleur, entiteEnCours, objs);
		controleurSort.nouveauTour(controleur, entiteEnCours, objs);
		oriAttaque = entiteEnCours.getCaracSpatiale().getOrientation();
		vue.nouveauTour(this, entiteEnCours);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		controleurDeplacement.finTour(controleur, entiteEnCours, objs);
		controleurSort.finTour(controleur, entiteEnCours, objs);
		vue.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		controleurDeplacement.enTour(controleur, entiteEnCours, objs);
		controleurSort.enTour(controleur, entiteEnCours, objs);
		vue.enTour(controleur, entiteEnCours, objs);
		
		//asupp
		if(Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			entiteEnCours.getCaracPhysique().setActu(Carac.TEMPSACTION, 100);
		}
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
	 * Lancé lors du survol d'une tuile. Calcul le nouveau chemin depuis
	 * l'entité active du tour.
	 *
	 * @param x
	 * @param y
	 */
	public void survolTuile(int x, int y) {
		if (entiteEnCours.getEtat() == Mode.DEPLACEMENT) {
			controleurDeplacement.survolTuile(x, y);
		} else if (entiteEnCours.getEtat() == Mode.SORT) {
			//Afficher zone action
			controleurSort.survolTuile(x, y);
		}
	}

	/**
	 * Lors d'un clic sur une tuile, l'envoie depuis vMap. Fait déplacer
	 * l'entité active.
	 *
	 * @param x
	 * @param y
	 */
	public void clicSurTuile(int x, int y) {
		if (entiteEnCours.getEtat() == Mode.DEPLACEMENT) {
			controleurDeplacement.clicSurTuile(x, y);
		} else if (entiteEnCours.getEtat() == Mode.SORT) {
			//Lancement de sort sur toute la zone action
			controleurSort.clicSurTuile(x, y);
			controleurDeplacement.modeDeplacement();
		}
	}

	public void addAction(Action action) {
		entiteEnCours.addAction(action);
		vue.addAction(action);
	}

	/**
	 * Lance les nouveaux tours, les fins tours
	 *
	 * Lance les actions sorts/déplacements
	 *
	 * @param o	Timeline EntiteActive
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Timeline) {
			Timeline tl = (Timeline) o;
			switch (tl.getEtatTour()) {
				case COURS:
					enTour(this, entiteEnCours);
					break;
				case DEBUT:
					entiteEnCours = tl.getEntiteEnCours();
					nouveauTour(this, entiteEnCours);
					break;
				case FIN:
					finTour(this, entiteEnCours);
					break;
			}
		} else if (o instanceof EntiteActive) {
			EntiteActive entite = (EntiteActive) o;
			if (arg instanceof Action) {
				Action action = (Action) arg;
				if (action.getEtat() == Action.EtatAction.DEPLACEMENT) {
					controleurDeplacement.update(entite, action);
				} else if (action.getEtat() == Action.EtatAction.ROTATION) {
					action.getSort().lancerSort(entite, map.getTabTuiles()[action.getPoint().x][action.getPoint().y], entite, action.getOriAttaque(), action.isCritique());
				} else if (action.getEtat() == Action.EtatAction.SORT) {
					controleurSort.update(entite, action, map);
				}
			}
		}
	}

	public Vue getVue() {
		return vue;
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
	public Orientation getOrientation(Point origine, Point point) {
		if (origine.equals(point)) {
			System.err.println(point);
			throw new IllegalArgumentException("Les deux points sont egaux ! " + point);
		}

		double vecX = point.getX() - origine.getX();
		double vecY = point.getY() - origine.getY();
//		System.out.println(vecX + " " + vecY);
		if (vecX > 0) {	//Est
			if (vecY > 0) {	//Sud
				if (vecX < vecY) {
					return Orientation.SUD;
				} else {
					return Orientation.EST;
				}
			} else {	//Nord
				if (vecX < vecY) {
					return Orientation.NORD;
				} else {
					return Orientation.EST;
				}
			}
		} else {	//Ouest
			if (vecY > 0) {	//Sud
				if (vecX < vecY) {
					return Orientation.SUD;
				} else {
					return Orientation.OUEST;
				}
			} else {	//Nord
				if (vecX > vecY) {
					return Orientation.NORD;
				} else {
					return Orientation.OUEST;
				}
			}
		}
	}

	public boolean isCoupCritique(Orientation oriCible, Orientation oriAttaque) {
		if (oriAttaque == null || oriCible == null || oriAttaque.equals(oriCible)) {
			return false;
		}
		return oriAttaque.invert().equals(oriCible);
	}

	public Orientation getOriAttaque() {
		return oriAttaque;
	}

	public void setOriAttaque(Orientation oriAttaque) {
		this.oriAttaque = oriAttaque;
	}

	public Orientation getPrecOriAttaque() {
		return precOriAttaque;
	}

	public void setPrecOriAttaque(Orientation precOriAttaque) {
		this.precOriAttaque = precOriAttaque;
	}
}
