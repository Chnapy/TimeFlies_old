/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.core.Timeline;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import gameplay.map.EtatTuile;
import gameplay.map.Tuile;
import java.awt.Point;
import vue.hud.minimap.vCase;
import vue.hud.timeline.vTimeline;
import vue.hud.vHud;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;
import vue.jeu.map.vTuile;
import vue.jeu.vJeu;

/**
 * vCombat.java
 *
 */
public class vCombat implements Screen {

	//Vue du jeu (map, entités)
	private final vJeu vjeu;

	//Vue du HUD (timeline, sorts, etc...)
	private final vHud vhud;

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 * @param personnages
	 * @param timel
	 */
	public vCombat(final cCombat ccombat, final Tuile[][] tabTuiles, final Array<Personnage> personnages, final Timeline timel) {
		vjeu = new vJeu(ccombat, tabTuiles, personnages);
		vhud = new vHud(ccombat, tabTuiles, personnages);

		//Accepter les input
		InputMultiplexer inputM = new InputMultiplexer(vjeu, vhud);
		Gdx.input.setInputProcessor(inputM);
	}

	/**
	 * Colore les tuiles selon les points pour le pathfinding
	 *
	 * @param listePoint
	 */
	public void colorTuile(Array<Point> listePoint) {
		vjeu.getVmap().colorTuile(listePoint);
		vhud.getVminimap().colorTuile(listePoint);
	}

	/**
	 * Affiche la portée d'un sort sur une zone donnée depuis la position de
	 * l'entité lanceuse.
	 *
	 * @param zone
	 * @param posEntite
	 */
	public void afficherPortee(boolean[][] zone, Point posEntite) {
		vjeu.getVmap().clearColorTuile();
		vhud.getVminimap().clearColorTuile();

		vTuile[][] tabVtuiles = vjeu.getVmap().getTabVtuiles();
		vCase[][] tabVcases = vhud.getVminimap().getTabVcases();

		for (int y = posEntite.y + zone.length / 2 - Math.abs(zone.length % 2 - 1), j = 0;
				y > posEntite.y - zone.length / 2 - zone.length % 2 && j < zone.length;
				y--, j++) {
			for (int x = posEntite.x - zone[0].length / 2 + Math.abs(zone[0].length % 2 - 1), i = 0;
					x < posEntite.x + zone[0].length / 2 + zone[0].length % 2 && i < zone[0].length;
					x++, i++) {
				if (zone[j][i] && y >= 0 && x >= 0 && y < tabVtuiles.length && x < tabVtuiles[0].length) {
					tabVtuiles[y][x].setEtat(EtatTuile.ZONESORT);
					tabVcases[y][x].setEtat(EtatTuile.ZONESORT);
				}
			}
		}
	}

	/**
	 * Affiche la zone d'action du sort depuis une zone donnée et une position
	 * cible
	 *
	 * @param zone
	 * @param cible
	 */
	public void afficherAction(boolean[][] zone, Point cible) {
		vjeu.getVmap().clearActionTuile();
		vhud.getVminimap().clearActionTuile();

		vTuile[][] tabVtuiles = vjeu.getVmap().getTabVtuiles();
		vCase[][] tabVcases = vhud.getVminimap().getTabVcases();

		for (int y = cible.y + zone.length / 2 - Math.abs(zone.length % 2 - 1), j = 0;
				y > cible.y - zone.length / 2 - zone.length % 2 && j < zone.length;
				y--, j++) {
			for (int x = cible.x - zone[0].length / 2 + Math.abs(zone[0].length % 2 - 1), i = 0;
					x < cible.x + zone[0].length / 2 + zone[0].length % 2 && i < zone[0].length;
					x++, i++) {
				if (zone[j][i] && y >= 0 && x >= 0 && y < tabVtuiles.length && x < tabVtuiles[0].length) {
					tabVtuiles[y][x].setAction(true);
					tabVcases[y][x].setAction(true);
				}
			}
		}
	}

	/**
	 * Nettoie toutes les tuiles d'éventuelles actions
	 */
	public void clearActionTuile() {
		vjeu.getVmap().clearActionTuile();
		vhud.getVminimap().clearActionTuile();
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels pathfinding
	 */
	public void clearColorTuile() {
		vjeu.getVmap().clearColorTuile();
		vhud.getVminimap().clearColorTuile();
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels pathfinding et actions
	 */
	public void clearAll() {
		vjeu.getVmap().clearAll();
		vhud.getVminimap().clearAll();
	}

	@Override
	public void show() {

	}

	/**
	 * Affichage des différentes vues
	 *
	 * @param delta	espace de temps entre 2 frames (utilisé pour
	 *              l'interpolation)
	 */
	@Override
	public void render(float delta) {
		vjeu.render();
		vhud.render();
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	/**
	 * Lancé lorsqu'un nouveau tour d'une entité commence
	 *
	 * @param controleur
	 * @param entiteEnCours
	 */
	public void nouveauTour(cCombat controleur, EntiteActive entiteEnCours) {
		vhud.nouveauTour(controleur, entiteEnCours);
		vjeu.nouveauTour();
	}

	/**
	 * Lancé lorsqu'un tour d'une entité finit
	 */
	public void finTour() {
		vhud.finTour();
		vjeu.finTour();
	}

	public vTimeline getVtimeline() {
		return vhud.getVtimeline();
	}

	public vMap getVmap() {
		return vjeu.getVmap();
	}

	public vEntites getVentites() {
		return vjeu.getVentites();
	}

	public vHud getVhud() {
		return vhud;
	}

}
