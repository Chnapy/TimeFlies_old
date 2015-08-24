/*
 * 
 * 
 * 
 */
package vue.hud.minimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.map.EtatTuile;
import gameplay.map.Tuile;
import java.awt.Point;
import vue.Couleur;
import vue.hud.vHud;

/**
 * vMinimap.java
 *
 */
public class vMinimap extends Table {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "minimap");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "minimap");

	//Taille et position de la minimap
	private static final int SIZE = 115;
	private static final int X = 1900 - SIZE;
	private static final int Y = 20;

	//Afficheur de forme pour les cases
	private static final ShapeRenderer shapeRender = new ShapeRenderer();

	//Taille d'une case
	private final int caseWidth;

	//Tableau de l'ensemble des cases
	private final vCase[][] tabVcases;

	public vMinimap(final cCombat controleur, Tuile[][] tabTuiles) {
		tabVcases = new vCase[tabTuiles.length][tabTuiles[0].length];
//		debugAll();
		setSize(SIZE, SIZE);
		setPosition(X, Y);
		caseWidth = SIZE / tabTuiles[0].length;
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
				tabVcases[y][x] = new vCase(controleur, x, y, t, tabTuiles[y][x].getEtat());
				add(tabVcases[y][x]).size(caseWidth + 2);
			}
			row();
		}
		pack();
	}

	@Override
	protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
		batch.end();

		vHud.drawBackground(X, Y, getWidth(), getHeight(), FOND_COULEUR, FOND_CONTOUR_COULEUR);

		//Batch
		batch.begin();
		super.drawBackground(batch, parentAlpha, x, y); //TODO
	}

	/**
	 * Colorie les tuiles du chemin visible.
	 *
	 * @param listePoint
	 */
	public void colorTuile(Array<Point> listePoint) {
		clearColorTuile();
		listePoint.forEach((Point point) -> {
			tabVcases[point.y][point.x].tuileDuChemin(true);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels pathfinding
	 */
	public void clearColorTuile() {
		getChildren().forEach((Actor vcase) -> {
			((vCase) vcase).tuileDuChemin(false);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuelles actions
	 */
	public void clearActionTuile() {
		getChildren().forEach((Actor vcase) -> {
			((vCase) vcase).setAction(false);
		});
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels actions ou pathfinding
	 */
	public void clearAll() {
		getChildren().forEach((Actor vcase) -> {
			((vCase) vcase).setEtat(EtatTuile.NORMAL);
			((vCase) vcase).tuileDuChemin(false);
			((vCase) vcase).setAction(false);
		});
	}

	/**
	 * Fin du tour de l'entité
	 */
	public void finTour() {
		clearAll();
	}

	public vCase[][] getTabVcases() {
		return tabVcases;
	}

}
