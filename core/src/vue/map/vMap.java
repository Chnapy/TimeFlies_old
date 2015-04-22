/*
 * 
 * 
 * 
 */
package vue.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.map.Tuile;
import java.awt.Point;
import test.MainTest;

/**
 * vMap.java
 *
 */
public class vMap extends Stage {

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
				getRoot().addActor(new vTuile(x, y, t, ccombat));
			}
		}

	}

	public void render() {
		setViewport(MainTest.viewport);
		act();
		draw();
	}

	/**
	 * Colorie les tuiles du chemin visible.
	 *
	 * @param listePoint
	 */
	public void colorTuile(Array<Point> listePoint) {
		clearColorTuile();
		getRoot().getChildren().forEach((Actor vtuile) -> {
			listePoint.forEach((Point point) -> {
				if (((vTuile) vtuile).getPosX() == point.x && ((vTuile) vtuile).getPosY() == point.y) {
					((vTuile) vtuile).tuileDuChemin(true);
				}
			});
		});
	}

	public void clearColorTuile() {
		getRoot().getChildren().forEach((Actor vtuile) -> {
			((vTuile) vtuile).tuileDuChemin(false);
		});
	}

}
