/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.utils.Array;

import gameplay.core.Joueur;
import gameplay.core.Timeline;
import gameplay.core.Tour;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import gameplay.map.Map;
import gameplay.map.Tuile;
import gameplay.map.Type;
import gameplay.sort.SortActif;

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

	private final vCombat vue;

	private final Map map;
	private final Joueur[] tabJoueurs;
	private final Timeline timeline;

	//Chemin (liste de points) de l'entité active à la tuile ciblée
	private Array<Point> path;

	private EntiteActive entiteEnCours;

	public cCombat(Map m, Joueur[] joueurs) {
		map = m;
		tabJoueurs = joueurs;
		Array<Personnage> listPersonnages = getPersonnages(joueurs);
		timeline = new Timeline(listPersonnages);
		vue = new vCombat(this, m.getTabTuiles(), listPersonnages, timeline);
		timeline.addObserver(this);
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

	public void nouveauTour() {
		vue.getVhud().nouveauTour(this, entiteEnCours);
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

	/**
	 * Lancé lors du survol d'une tuile.
	 * Calcul le nouveau chemin depuis l'entité active du tour.
	 *
	 * @param x
	 * @param y
	 */
	public void survolTuile(int x, int y) {
		Tuile tuile = map.getTabTuiles()[y][x];
//		System.out.println(tuile.getEtat());

		if (entiteEnCours.isModeDeplacement()) {

			vue.getVmap().clearColorTuile();
			//Déplacement
			if (!entiteEnCours.isEnDeplacement()
					&& !entiteEnCours.getCaracSpatiale().getPosition().equals(tuile.getPosition())
					&& !tuile.getType().equals(Type.OBSTACLE)
					&& !tuile.getType().equals(Type.TROU)) {
				path = map.getChemin(entiteEnCours.getCaracSpatiale().getPosition(), new Point(x, y));
				if (path != null) {
					vue.getVmap().colorTuile(path);
				}
			} else if (path != null) {
				path = null; //Purge
			}
		} else {
			//Afficher zone portée
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

		if (entiteEnCours.isModeDeplacement()) {
			//Déplacement
			if (!entiteEnCours.isEnDeplacement() && path != null) {
				vue.getVmap().clearColorTuile();
				entiteEnCours.setEnDeplacement(true);
				entiteEnCours.setPosition(path);
				path = null; //Purge
			}
		} else {
			//Lancement de sort
		}
	}

	/**
	 * Passage en mode sort
	 *
	 * @param index
	 */
	public void modeSort(int index) {
		entiteEnCours.setModeDeplacement(false);
		SortActif sort = entiteEnCours.setSortEnCours(index);
		vue.getVmap().afficherPortee(sort.getZonePortee().getZoneFinale(), entiteEnCours.getCaracSpatiale().getPosition());	//TODO
	}

	@Override
	public void update(Observable o, Object arg) {
		Timeline tl = (Timeline) o;
		if (tl.getEtatTour().equals(Tour.DEBUT)) {
			entiteEnCours = tl.getEntiteEnCours();
			nouveauTour();
		} else if (tl.getEtatTour().equals(Tour.FIN)) {
			vue.getVhud().finTour();
		}
	}

	public vCombat getVue() {
		return vue;
	}

	
	/**
	 * permet de lancer un sort sur la tuile tuileCible
	 * 
	 * @param lanceur
	 * @param sort
	 * @param tuileCible
	 */
	public void lancerSort(Entite lanceur, SortActif sort, Tuile tuileCible){
		Personnage persoCible = getPerso(tuileCible);
		if(persoCible==null)
			tuileCible.recoitSort(sort.getTabEffets(), lanceur);
		else
			persoCible.recoitSort(sort.getTabEffets(), lanceur);
	}
	
	/**
	 * 
	 * @param tuile
	 * @return le personnage présent sur la tuile null si vide
	 */
	public Personnage getPerso(Tuile tuile){
		Personnage[] persos=null;
		for (int i = 0; i < tabJoueurs.length; i++) {
			persos = tabJoueurs[i].getPersonnages();
			for (int k = 0; k < persos.length; k++) {
				if(persos[k].getCaracSpatiale().getPosition().equals(tuile.getPosition()))
					return persos[k];
			}
		}
		return null;
	}
}
