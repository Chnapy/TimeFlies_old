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
import gameplay.map.Tuile;
import gameplay.sort.zone.ZonePortee;
import java.awt.Point;

/**
 * vMap.java
 *
 */
public class vMap extends Group {

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 */
	public vMap(final cCombat ccombat, final Tuile[][] tabTuiles) {
		int x, y, t;
		for (y = 0; y < tabTuiles.length; y++) {
			for (x = 0; x < tabTuiles[0].length; x++) {
				switch (tabTuiles[y][x].getEtat()) {
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
				addActor(new vTuile(x, y, t, ccombat));
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
		getChildren().forEach((Actor vtuile) -> {
			listePoint.forEach((Point point) -> {
				if (((vTuile) vtuile).getPosX() == point.x && ((vTuile) vtuile).getPosY() == point.y) {
					((vTuile) vtuile).tuileDuChemin(true);
				}
			});
		});
	}
	
	public void afficherPortee(ZonePortee portee, Point posPerso) {
		//TODO
	}

	public void clearColorTuile() {
		getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).tuileDuChemin(false);
		});
	}

}
