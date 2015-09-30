/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import controleur.ControleurPrincipal;
import gameplay.core.Timeline;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.EtatTuile;
import gameplay.map.Tuile;
import gameplay.sort.pileaction.Action;
import general.Tourable;
import java.awt.Point;
import test.MainTest;
import vue.hud.minimap.vCase;
import vue.hud.timeline.vTimeline;
import vue.hud.vHud;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;
import vue.jeu.map.vTuile;
import vue.jeu.vJeu;

/**
 * Vue.java
 *
 */
public class Vue implements Screen, Tourable {

	public static Vector3 mouse_position = new Vector3();

	private static final String[] assetsPath = {
		"sort/sort_fond.png",
		"icons/cooldown.png",
		"icons/degats.png",
		"icons/fatigue.png",
		"icons/nom.png",
		"icons/tempsaction.png",
		"icons/tempssupp.png",
		"icons/vie.png",
		"icons/vitesseaction.png",
		"icons/zoneaction.png",
		"icons/zoneportee.png",
		"sort_fx/SortQuiFaitMal_0/cercle.png",
		"timeline/perso1.png",
		"timeline/perso2.png"
	};

	private final AssetManager manager;

	//Vue du jeu (map, entités)
	private final vJeu vjeu;

	//Vue du HUD (timeline, sorts, etc...)
	private final vHud vhud;

	//Curseur de la souris
	private final Curseur curseur;

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 * @param entites
	 * @param timel
	 */
	public Vue(ControleurPrincipal ccombat, Tuile[][] tabTuiles, Array<Entite> entites, Timeline timel) {
		manager = new AssetManager();
		loadAllAssets();
		vjeu = new vJeu(ccombat, tabTuiles, entites, manager);
		vhud = new vHud(ccombat, tabTuiles, entites, manager);

		curseur = new Curseur();
		vhud.addActor(curseur);

		//Accepter les input
		InputMultiplexer inputM = new InputMultiplexer(vjeu, vhud);
		Gdx.input.setInputProcessor(inputM);
	}

	public void addEntite(Entite entite) {
		vjeu.addEntite(entite);
		vhud.addEntite(entite);
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
					tabVtuiles[y][x].setEtat(EtatTuile.ZONEPORTEE);
					tabVcases[y][x].setEtat(EtatTuile.ZONEPORTEE);
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
		mouse_position = MainTest.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f));
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

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vhud.nouveauTour(controleur, entiteEnCours, objs);
		vjeu.nouveauTour(controleur, entiteEnCours, objs);
		curseur.nouveauTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vhud.finTour(controleur, entiteEnCours, objs);
		vjeu.finTour(controleur, entiteEnCours, objs);
		curseur.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vhud.enTour(controleur, entiteEnCours, objs);
		vjeu.enTour(controleur, entiteEnCours, objs);
		curseur.enTour(controleur, entiteEnCours, objs);
	}

	public void addAction(Action action) {
		vhud.addAction(action);
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

	public vJeu getVjeu() {
		return vjeu;
	}

	private final void loadAllAssets() {
		TextureParameter param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		param.genMipMaps = true;
		long temps = System.currentTimeMillis();
		System.out.print("--Chargement des textures...");

		try {
			for (String path : assetsPath) {
				manager.load(path, Texture.class, param);
			}
			manager.finishLoading();

			temps = System.currentTimeMillis() - temps;
			System.out.println(" terminé [" + temps + "ms]");
		} catch (GdxRuntimeException e) {
			System.out.println("FAIL");
			throw e;
		}
	}
	
	public static float getAbsoluteX(Actor a) {
		Actor actor = a;
		float x = actor.getX();
		while(actor.hasParent()) {
			actor = actor.getParent();
			x += actor.getX();
		}
		return x;
	}
	
	public static float getAbsoluteY(Actor a) {
		Actor actor = a;
		float y = actor.getY();
		while(actor.hasParent()) {
			actor = actor.getParent();
			y += actor.getY();
		}
		return y;
	}

}
