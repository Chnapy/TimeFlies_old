/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortsactifs;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import gameplay.sort.SortActif;
import general.Tourable;
import static test.MainTest.MAX_WIDTH;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;
import vue.hud.sorts.vSortsBouton;

/**
 * vBarreSortsActifs.java
 *
 */
public class vBarreSortsActifs extends Bloc implements Tourable {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "sort", "actif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "sort", "actif");

	//Position et taille de la barre
	private static final int WIDTH = 800;
	private static final int HEIGHT = 128;
	private static final int X = MAX_WIDTH - WIDTH - 160;
	private static final int Y = 12;

	private final AssetManager manager;

	public vBarreSortsActifs(AssetManager _manager) {
		super("Sorts actifs", WIDTH, HEIGHT);
		manager = _manager;
		setPosition(X, Y);
		this.align(Align.left);
//		filtrerTextures();
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
		this.add(bouton).left().padLeft(20);
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		getCells().clear();
		while (getChildren().size > 1) {
			((vSortsActifsBouton) getChildren().get(1)).clearSortObserver();
			getChildren().removeIndex(1);
		}
		for (SortActif sort : entiteEnCours.getTabSortActif()) {
			System.out.println(sort.getCooldownActu());
			addBouton(new vSortsActifsBouton(controleur, sort, manager));
		}
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
