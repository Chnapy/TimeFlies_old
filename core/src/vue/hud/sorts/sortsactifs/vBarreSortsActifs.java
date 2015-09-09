/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortsactifs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import controleur.cCombat;
import gameplay.sort.SortActif;
import static test.MainTest.MAX_WIDTH;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;
import vue.hud.sorts.vSortsBouton;

/**
 * vBarreSortsActifs.java
 *
 */
public class vBarreSortsActifs extends Bloc {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "sort", "actif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "sort", "actif");

	//Position et taille de la barre
	private static final int WIDTH = 800;
	private static final int HEIGHT = 128;
	private static final int X = MAX_WIDTH - WIDTH - 152;
	private static final int Y = 12;

	public vBarreSortsActifs() {
		super("Sorts actifs", WIDTH, HEIGHT);
		setPosition(X, Y);
		vSortsActifsBouton.filterTexture();
		this.align(Align.left);
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "Cette barre liste les sorts actifs utilisables par l'entite active.";
			}
		});
	}

	/**
	 * Ajoute un bouton de sort à la barre
	 *
	 * @param bouton
	 */
	public void addBouton(vSortsBouton bouton) {
		this.add(bouton).left().padLeft(16);
	}

	/**
	 * Nouveau tour d'une entité
	 *
	 * @param ccombat
	 * @param sactifs
	 */
	public void nouveauTour(cCombat ccombat, SortActif[] sactifs) {
		for (SortActif sort : sactifs) {
			addBouton(new vSortsActifsBouton(ccombat, sort.getIndex(), 5, 10, 8, 2, sort.getDescription()));
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

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

}
