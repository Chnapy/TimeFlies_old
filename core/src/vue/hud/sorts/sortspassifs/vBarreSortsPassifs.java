/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import controleur.cCombat;
import gameplay.sort.SortPassif;
import static test.MainTest.MAX_WIDTH;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.sorts.vSortsBouton;
import vue.hud.vHud;
import static vue.hud.vHud.FONT;
import static vue.hud.vHud.FONT_COLOR;

/**
 * vBarreSortsPassifs.java
 *
 */
public class vBarreSortsPassifs extends Bloc {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "sort", "passif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "sort", "passif");

	//Position et taille de la barre
	private static final int WIDTH = 128;
	private static final int HEIGHT = 800;
	private static final int X = MAX_WIDTH - WIDTH - 12;
	private static final int Y = 152;

	public vBarreSortsPassifs() {
		super("Sorts passifs", WIDTH, HEIGHT);
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
//		debugAll();
	}

	@Override
	public void render(Batch batch, float parentAlpha) {
	}

	//Ajout d'un bouton de sort
	public void addBouton(vSortsBouton bouton) {
		this.add(bouton).top().padTop(16);
	}

	/**
	 * Nouveau tour d'une entité
	 *
	 * @param ccombat
	 * @param spassifs
	 */
	public void nouveauTour(cCombat ccombat, SortPassif[] spassifs) {
		for (SortPassif sort : spassifs) {
			addBouton(new vSortsPassifsBouton(sort.getIndex()));
		}
	}

	/**
	 * Fin tour d'une entité
	 *
	 */
	public void finTour() {
		getCells().clear();
		while (getChildren().size > 1) {
			getChildren().removeIndex(1);
		}
	}

}
