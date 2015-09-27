/*
 * 
 * 
 * 
 */
package vue.jeu;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;
import general.Tourable;
import java.awt.Point;
import test.MainTest;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;
import vue.jeu.sorts.vSorts;

/**
 * vJeu.java
 *
 */
public class vJeu extends Stage implements Tourable {

	//Controleur
	private final ControleurPrincipal combat;

	//Vue de la map
	private final vMap vmap;

	//Vue de toutes les entites
	private final vEntites ventites;

	//Vue des sorts
	private final vSorts vsorts;

	public vJeu(final ControleurPrincipal ccombat, final Tuile[][] tabTuiles, final Array<Personnage> personnages, AssetManager manager) {
		combat = ccombat;
		vmap = new vMap(ccombat, tabTuiles);
		ventites = new vEntites(personnages);
		vsorts = new vSorts(manager);
		addActor(vmap);
		addActor(ventites);
		addActor(vsorts);
	}

	/**
	 * Affichage
	 */
	public void render() {
		setViewport(MainTest.viewport);
		act();
		draw();
	}

	public void addSort(int index, int tempsAction, Point start, Point end) {
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
