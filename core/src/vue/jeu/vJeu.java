/*
 * 
 * 
 * 
 */
package vue.jeu;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;
import java.awt.Point;
import test.MainTest;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;
import vue.jeu.sorts.vSorts;

/**
 * vJeu.java
 *
 */
public class vJeu extends Stage {

	//Controleur
	private final cCombat combat;

	//Vue de la map
	private final vMap vmap;

	//Vue de toutes les entites
	private final vEntites ventites;

	//Vue des sorts
	private final vSorts vsorts;

	public vJeu(final cCombat ccombat, final Tuile[][] tabTuiles, final Array<Personnage> personnages) {
		combat = ccombat;
		vmap = new vMap(ccombat, tabTuiles);
		ventites = new vEntites(personnages);
		vsorts = new vSorts();

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

	/**
	 * Lorsqu'une entité commence son tour
	 */
	public void nouveauTour() {

	}

	/**
	 * Lorsqu'une entité finit son tour
	 */
	public void finTour() {
		vmap.finTour();
	}

	public cCombat getCombat() {
		return combat;
	}

	public vMap getVmap() {
		return vmap;
	}

	public vEntites getVentites() {
		return ventites;
	}

}
