/*
 * 
 * 
 * 
 */
package vue.hud.minimap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.entite.EntiteActive;
import gameplay.map.EtatTuile;
import gameplay.map.Tuile;
import general.Tourable;
import java.awt.Point;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;

/**
 * vMinimap.java
 *
 */
public class vMinimap extends Bloc implements Tourable {

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
		super("Minimap", SIZE, SIZE);
		tabVcases = new vCase[tabTuiles.length][tabTuiles[0].length];
//		debugAll();
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
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "La minimap permet d'avoir une vue d'ensemble de la map.\nVous pouvez interagir avec celle-ci.";
			}
		});
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
		vCase vcase;
		for (int i = 1; i < getChildren().size; i++) {
			vcase = (vCase) getChildren().get(i);
			vcase.tuileDuChemin(false);
		}
	}

	/**
	 * Nettoie toutes les tuiles d'éventuelles actions
	 */
	public void clearActionTuile() {
		vCase vcase;
		for (int i = 1; i < getChildren().size; i++) {
			vcase = (vCase) getChildren().get(i);
			vcase.setAction(false);
		}
	}

	/**
	 * Nettoie toutes les tuiles d'éventuels actions ou pathfinding
	 */
	public void clearAll() {
		vCase vcase;
		for (int i = 1; i < getChildren().size; i++) {
			vcase = (vCase) getChildren().get(i);
			vcase.setEtat(EtatTuile.NORMAL);
			vcase.tuileDuChemin(false);
			vcase.setAction(false);
		}
	}

	public vCase[][] getTabVcases() {
		return tabVcases;
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

	@Override
	public void nouveauTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void finTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
		clearAll();
	}

	@Override
	public void enTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
