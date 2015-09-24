/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.hud.sorts.sortsactifs.vBarreSortsActifs;
import vue.hud.sorts.sortspassifs.vBarreSortsPassifs;

/**
 * vSortsBout.java
 *
 */
public class vSortsBout extends Group implements Tourable {

	//Vue des sorts actifs
	private final vBarreSortsActifs barreSortsActifs;

	//Vue des sorts passifs
	private final vBarreSortsPassifs barreSortsPassifs;

	public vSortsBout(AssetManager manager) {
		barreSortsActifs = new vBarreSortsActifs(manager);
		barreSortsPassifs = new vBarreSortsPassifs(manager);
		addActor(barreSortsActifs);
		addActor(barreSortsPassifs);
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		barreSortsActifs.nouveauTour(controleur, entiteEnCours, objs);
		barreSortsPassifs.nouveauTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		barreSortsActifs.finTour(controleur, entiteEnCours, objs);
		barreSortsPassifs.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		barreSortsActifs.enTour(controleur, entiteEnCours, objs);
		barreSortsPassifs.enTour(controleur, entiteEnCours, objs);
	}

}
