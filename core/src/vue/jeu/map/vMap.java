/*
 * 
 * 
 * 
 */
package vue.jeu.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.map.EtatTuile;
import gameplay.map.Tuile;
import java.awt.Point;

/**
 * vMap.java
 *
 */
public class vMap extends Group {

	//Tableau des tuiles de la map
	private final vTuile[][] tabVtuiles;

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 */
	public vMap(final cCombat ccombat, final Tuile[][] tabTuiles) {
		tabVtuiles = new vTuile[tabTuiles.length][tabTuiles[0].length];
		int x, y, t;
		for (y = 0; y < tabTuiles.length; y++) {
			for (x = 0; x < tabTuiles[0].length; x++) {
				switch (tabTuiles[y][x].getType()) {
					case SIMPLE:
						t = 0;
						break;
					case TROU:
						t = 1;
						break;
					case OBSTACLE:
						t = 2;
						break;
					case ECRAN:
						t = 3;
						break;
					default:
						throw new Error("Tuile non gérée");
				}
				tabVtuiles[y][x] = new vTuile(x, y, t, tabTuiles[y][x].getEtat(), ccombat);
				addActor(tabVtuiles[y][x]);
			}
		}

	}

	/**
	 * Colorie les tuiles du chemin visible.
	 *
	 * @param listePoint
	 */
	public void colorTuile(Array<Point> listePoint) {
		clearColorTuile();
		listePoint.forEach((Point point) -> {
			tabVtuiles[point.y][point.x].tuileDuChemin(true);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels pathfinding
	 */
	public void clearColorTuile() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).tuileDuChemin(false);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuelles actions
	 */
	public void clearActionTuile() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).setAction(false);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels actions ou pathfinding
	 */
	public void clearAll() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).setEtat(EtatTuile.NORMAL);
			((vTuile) vtuile).tuileDuChemin(false);
			((vTuile) vtuile).setAction(false);
		});
	}

	/**
	 * Fin du tour de l'entité
	 */
	public void finTour() {
		clearAll();
	}

	public vTuile[][] getTabVtuiles() {
		return tabVtuiles;
	}

}
