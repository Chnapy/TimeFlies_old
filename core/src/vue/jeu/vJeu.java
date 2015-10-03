/*
 * 
 * 
 * 
 */
package vue.jeu;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import general.Tourable;
import test.MainTest;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;
import vue.jeu.sorts.vSorts;

/**
 * vJeu.java
 *
 */
public class vJeu extends Stage implements Tourable {
	
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1020;
	private static final int X = (1920 - WIDTH) / 2;
	private static final int Y = (1080 - HEIGHT) / 2;

	//Controleur
	private final ControleurPrincipal combat;

	//Vue de la map
	private final vMap vmap;

	//Vue de toutes les entites
	private final vEntites ventites;

	//Vue des sorts
	private final vSorts vsorts;

	public vJeu(ControleurPrincipal ccombat, Tuile[][] tabTuiles, Array<? extends Entite> entites, AssetManager manager) {
		combat = ccombat;
		vmap = new vMap(ccombat, tabTuiles, WIDTH, HEIGHT, X, Y);
		ventites = new vEntites(entites, WIDTH, HEIGHT, X, Y);
		vsorts = new vSorts(manager, WIDTH, HEIGHT, X, Y);
		
		addActor(vmap);
		addActor(ventites);
		addActor(vsorts);
	}

	public void addEntite(Entite entite) {
		ventites.addEntite(entite);
	}

	/**
	 * Affichage
	 */
	public void render() {
		setViewport(MainTest.viewport);
		act();
		draw();
	}

	public void addSort(int index, int tempsAction, GridPoint2 start, GridPoint2 end) {
		vsorts.addSort(index, tempsAction, start, end);
	}

	public void removeSort() {
		vmap.clearGhostZoneAction();
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vmap.nouveauTour(controleur, entiteEnCours, objs);
		ventites.nouveauTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vmap.finTour(controleur, entiteEnCours, objs);
		ventites.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vmap.enTour(controleur, entiteEnCours, objs);
		ventites.enTour(controleur, entiteEnCours, objs);
	}

	public ControleurPrincipal getCombat() {
		return combat;
	}

	public vMap getVmap() {
		return vmap;
	}

	public vEntites getVentites() {
		return ventites;
	}

}
