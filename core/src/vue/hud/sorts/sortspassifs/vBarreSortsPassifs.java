/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import gameplay.sort.SortPassif;
import general.Tourable;
import static test.MainTest.MAX_WIDTH;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;
import vue.hud.sorts.vSortsBouton;

/**
 * vBarreSortsPassifs.java
 *
 */
public class vBarreSortsPassifs extends Bloc implements Tourable {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "sort", "passif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "sort", "passif");

	//Position et taille de la barre
	private static final int WIDTH = 128;
	private static final int HEIGHT = 750;
	private static final int X = MAX_WIDTH - WIDTH - 20;
	private static final int Y = 170;

	public vBarreSortsPassifs(AssetManager manager) {
		super("Sorts passifs", WIDTH, HEIGHT, manager);
		setPosition(X, Y);
		align(Align.top);
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "Cette barre liste les sorts passifs de l'entite active.";
			}
		});
//		debugAll();
	}

	@Override
	public void render(Batch batch, float parentAlpha) {
	}

	//Ajout d'un bouton de sort
	public void addBouton(vSortsBouton bouton) {
		this.add(bouton).top().padTop(30).row();
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		getCells().clear();
		while (getChildren().size > 1) {
			((vSortsPassifsBouton) getChildren().get(1)).delete();
			getChildren().removeIndex(1);
		}
		for (SortPassif sort : entiteEnCours.getTabSortPassif()) {
			addBouton(new vSortsPassifsBouton(controleur, sort, manager));
		}
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
