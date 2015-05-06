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

	public void afficherPortee(boolean[][] zone, Point posEntite) {
		clearColorTuile();

		for (int y = posEntite.y + zone.length / 2 - Math.abs(zone.length % 2 - 1), j = 0;
				y > posEntite.y - zone.length / 2 - zone.length % 2 && j < zone.length;
				y--, j++) {
			for (int x = posEntite.x - zone[0].length / 2 + Math.abs(zone[0].length % 2 - 1), i = 0;
					x < posEntite.x + zone[0].length / 2 + zone[0].length % 2 && i < zone[0].length;
					x++, i++) {
				if (zone[j][i] && y >= 0 && x >= 0 && y < tabVtuiles.length && x < tabVtuiles[0].length) {
					tabVtuiles[y][x].setEtat(EtatTuile.ZONESORT);
				}
			}
		}
	}

	public void afficherAction(boolean[][] zone, Point cible) {
		clearActionTuile();

		for (int y = cible.y + zone.length / 2 - Math.abs(zone.length % 2 - 1), j = 0;
				y > cible.y - zone.length / 2 - zone.length % 2 && j < zone.length;
				y--, j++) {
			for (int x = cible.x - zone[0].length / 2 + Math.abs(zone[0].length % 2 - 1), i = 0;
					x < cible.x + zone[0].length / 2 + zone[0].length % 2 && i < zone[0].length;
					x++, i++) {
				if (zone[j][i] && y >= 0 && x >= 0 && y < tabVtuiles.length && x < tabVtuiles[0].length) {
					tabVtuiles[y][x].setAction(true);
				}
			}
		}
	}

	public void clearColorTuile() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).tuileDuChemin(false);
		});
	}

	public void clearActionTuile() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).setAction(false);
		});
	}
	
	public void clearAll() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).tuileDuChemin(false);
			((vTuile) vtuile).setAction(false);
		});
	}
	
	public void finTour() {
		clearAll();
	}

	public vTuile[][] getTabVtuiles() {
		return tabVtuiles;
	}

}
