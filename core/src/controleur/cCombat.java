/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.utils.Array;
import gameplay.core.Joueur;
import gameplay.core.Timeline;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import gameplay.map.Map;
import gameplay.map.Tuile;
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
public class cCombat {
	
	private vCombat vue;
	
	private final Map map;
	private final Joueur[] tabJoueurs;
	private final Timeline timeline;
	
	public cCombat(Map m, Joueur[] joueurs) {
		map = m;
		tabJoueurs = joueurs;
		Array<Personnage> listPersonnages = getPersonnages(joueurs);
		timeline = new Timeline(listPersonnages);
		vue = new vCombat(this, m.getTabTuiles(), listPersonnages, timeline);
		timeline.addObserver(vue.getVTimeline());
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
	 * Lors d'un clic sur une tuile, l'envoie depuis vMap.
	 * 
	 * @param x
	 * @param y
	 */
	public void clicSurTuile(int x, int y) {
		Tuile tuile = map.getTabTuiles()[y][x];
		System.out.println(tuile.getEtat());
		
		EntiteActive ent = timeline.getEntiteEnCours();
		if(ent.isDeplacer()) {
			//Déplacement
			ent.setPosition(x, y);
		} else {
			//Lancement de sort
		}
	}

	public vCombat getVue() {
		return vue;
	}

}
