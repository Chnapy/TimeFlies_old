/*
 * 
 * 
 * 
 */
package vue.hud.minimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

/**
 * vMinimap.java
 *
 */
public class vMinimap extends Table {
	
	private static final Texture BACKGROUND = new Texture(Gdx.files.internal("minimap/minimap_back.png"));

	private static final int SIZE = 115;
	private static final int X = 1900 - SIZE;
	private static final int Y = 20;
	private static final ShapeRenderer shapeRender = new ShapeRenderer();

	private final int caseWidth;

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
		this.setBackground(new TextureRegionDrawable(new TextureRegion(BACKGROUND)));
		pack();
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

	public void clearColorTuile() {
		getChildren().forEach((Actor vcase) -> {
			((vCase) vcase).tuileDuChemin(false);
		});
	}

	public void clearActionTuile() {
		getChildren().forEach((Actor vcase) -> {
			((vCase) vcase).setAction(false);
		});
	}
	
	public void clearAll() {
		getChildren().forEach((Actor vcase) -> {
			((vCase) vcase).setEtat(EtatTuile.NORMAL);
			((vCase) vcase).tuileDuChemin(false);
			((vCase) vcase).setAction(false);
		});
	}
	
	public void finTour() {
		clearAll();
	}

	public vCase[][] getTabVcases() {
		return tabVcases;
	}

}
