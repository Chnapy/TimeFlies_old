/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortsactifs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import controleur.cCombat;
import gameplay.sort.SortActif;
import static test.MainTest.MAX_WIDTH;
import vue.Couleur;
import vue.hud.sorts.vSortsBouton;
import vue.hud.vHud;
import static vue.hud.vHud.FONT;
import static vue.hud.vHud.FONT_COLOR;

/**
 * vBarreSortsActifs.java
 *
 */
public class vBarreSortsActifs extends HorizontalGroup {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "sort", "actif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "sort", "actif");

	//Position et taille de la barre
	private static final int WIDTH = 800;
	private static final int HEIGHT = 128;
	private static final int X = MAX_WIDTH - WIDTH - 152;
	private static final int Y = 12;

	private final ShapeRenderer shapeRenderer;

	public vBarreSortsActifs() {
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		shapeRenderer = new ShapeRenderer();
		vSortsActifsBouton.filterTexture();
		this.padLeft(15);
//		debugAll();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		vHud.drawBackground(X, Y, getWidth(), getHeight(), FOND_COULEUR, FOND_CONTOUR_COULEUR);

		//Batch
		batch.begin();
		super.draw(batch, parentAlpha);
		FONT.setColor(FONT_COLOR);
		FONT.drawMultiLine(batch, "SORTS ACTIFS", X + 14, Y + getHeight() - 10);
	}

	/**
	 * Ajoute un bouton de sort à la barre
	 *
	 * @param bouton
	 */
	public void addBouton(vSortsBouton bouton) {
		this.addActor(bouton);
		this.align(Align.left);
		this.space(16);
		this.padTop(16);
	}

	/**
	 * Nouveau tour d'une entité
	 *
	 * @param ccombat
	 * @param sactifs
	 */
	public void nouveauTour(cCombat ccombat, SortActif[] sactifs) {
		for (SortActif sort : sactifs) {
			addBouton(new vSortsActifsBouton(ccombat, sort.getIndex(), 5, 10, 8, 2));
		}
	}

	/**
	 * Fin tour d'une entité
	 *
	 */
	public void finTour() {
		getChildren().removeRange(0, getChildren().size - 1);
	}

}
