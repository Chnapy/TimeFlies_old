/*
 * 
 * 
 * 
 */
package vue.jeu.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import general.GridPointFloat2;
import general.Tourable;
import test.MainTest;

/**
 * vMap.java
 *
 */
public class vMap extends Group implements Tourable {

	public static float TUILE_WIDTH;
	public static float TUILE_HEIGHT;
	private static float MAP_HEIGHT;
	private static int tabHeight;
	private static int tabWidth;

	private final Batch polyBatch = new PolygonSpriteBatch();

	//Tableau des tuiles de la map
	private final vTuile[][] tabVtuiles;

	/**
	 *
	 * @param ccombat
	 * @param tabTuiles
	 * @param width
	 * @param height
	 * @param posX
	 * @param posY
	 */
	public vMap(final ControleurPrincipal ccombat, final Tuile[][] tabTuiles, int width, int height, int posX, int posY) {
		polyBatch.setProjectionMatrix(MainTest.camera.combined);
		setSize(width, height);
		setPosition(posX, posY);

		tabHeight = tabTuiles.length;
		tabWidth = tabTuiles[0].length;

		MAP_HEIGHT = getHeight();
		TUILE_WIDTH = getWidth() / ((float) (tabWidth + tabHeight) / 2);
		TUILE_HEIGHT = TUILE_WIDTH / 2;

		GridPointFloat2 pos;
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
				tabVtuiles[y][x] = new vTuile(x, y, t, tabTuiles[y][x].getEtat(), ccombat, TUILE_WIDTH, TUILE_HEIGHT);
				addActor(tabVtuiles[y][x]);
				pos = getTuilePosition(x, y);
				tabVtuiles[y][x].setPosition(pos.x, pos.y);
			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		polyBatch.begin();
		super.draw(polyBatch, parentAlpha);
		polyBatch.end();
		batch.begin();
	}

	/**
	 * Colorie les tuiles du chemin visible.
	 *
	 * @param listePoint
	 */
	public void colorTuile(Array<GridPoint2> listePoint) {
		clearColorTuile();
		listePoint.forEach((GridPoint2 point) -> {
			tabVtuiles[point.y][point.x].addPath();
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels pathfinding
	 */
	public void clearColorTuile() {
		getChildren().forEach((vtuile) -> {
			((vTuile) vtuile).clearPath();
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuelles actions
	 */
	public void clearActionTuile() {
		getChildren().forEach((vtuile) -> {
			((vTuile) vtuile).setAction(false);
		});
	}

	public void clearGhostZoneAction() {
		getChildren().forEach((vtuile) -> {
			((vTuile) vtuile).clearGhostZoneAction();
		});
	}

	public void clearMiniTuiles() {
		getChildren().forEach((vtuile) -> {
			((vTuile) vtuile).clearPath();
			((vTuile) vtuile).setAction(false);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels actions ou pathfinding
	 */
	public void clearAll() {
		getChildren().forEach((vtuile) -> {
			((vTuile) vtuile).clearPath();
			((vTuile) vtuile).setAction(false);
			((vTuile) vtuile).clearGhostZoneAction();
			((vTuile) vtuile).clearGhostPath(true);
		});
	}

	public static GridPointFloat2 getTuilePosition(int x, int y) {
		return new GridPointFloat2(
				x * TUILE_WIDTH / 2 + y * TUILE_WIDTH / 2,
				MAP_HEIGHT - ((tabHeight + 1) * TUILE_HEIGHT / 2 + x * TUILE_HEIGHT / 2 - y * TUILE_HEIGHT / 2)
		);
	}

	public vTuile[][] getTabVtuiles() {
		return tabVtuiles;
	}

	public void ghostPath(Array<GridPoint2> path) {
		path.forEach((pt) -> {
			tabVtuiles[pt.y][pt.x].addGhostPath();
		});
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		clearAll();
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
