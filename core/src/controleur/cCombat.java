/*
 * 
 * 
 * 
 */
package controleur;

import com.badlogic.gdx.utils.Array;
import gameplay.core.Joueur;
import gameplay.entite.Personnage;
import gameplay.map.Map;
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
	
	public cCombat(Map m, Joueur[] joueurs) {
		map = m;
		tabJoueurs = joueurs;
		vue = new vCombat(m.getTabTuiles(), getPersonnages(joueurs));
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

	public vCombat getVue() {
		return vue;
	}

}
