/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import controleur.cCombat;
import gameplay.sort.SortPassif;
import static test.MainTest.MAX_WIDTH;
import vue.Couleur;
import vue.hud.sorts.vSortsBouton;
import vue.hud.vHud;
import static vue.hud.vHud.FONT;
import static vue.hud.vHud.FONT_COLOR;

/**
 * vBarreSortsPassifs.java
 *
 */
public class vBarreSortsPassifs extends VerticalGroup {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "sort", "passif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "sort", "passif");

	//Position et taille de la barre
	private static final int WIDTH = 128;
	private static final int HEIGHT = 800;
	private static final int X = MAX_WIDTH - WIDTH - 12;
	private static final int Y = 152;

	private final ShapeRenderer shapeRenderer;

	public vBarreSortsPassifs() {
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		shapeRenderer = new ShapeRenderer();
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
		FONT.drawMultiLine(batch, "SORTS\nPASSIFS", getX() + 14, getY() + getHeight() - 10);
	}

	//Ajout d'un bouton de sort
	public void addBouton(vSortsBouton bouton) {
		this.addActor(bouton);
		this.align(Align.top);
		this.space(16);
		this.padTop(50);
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
		if (getChildren().size > 0) {
			getChildren().removeRange(0, getChildren().size - 1);
		}
	}

}
