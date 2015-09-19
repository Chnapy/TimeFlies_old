/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.scenes.scene2d.Group;
import controleur.cCombat;
import gameplay.entite.EntiteActive;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
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

	public vSortsBout() {
		barreSortsActifs = new vBarreSortsActifs();
		barreSortsPassifs = new vBarreSortsPassifs();
		addActor(barreSortsActifs);
		addActor(barreSortsPassifs);
	}

	@Override
	public void nouveauTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
		barreSortsActifs.nouveauTour(controleur, entiteEnCours, objs);
		barreSortsPassifs.nouveauTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
		barreSortsActifs.finTour(controleur, entiteEnCours, objs);
		barreSortsPassifs.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs) {
		barreSortsActifs.enTour(controleur, entiteEnCours, objs);
		barreSortsPassifs.enTour(controleur, entiteEnCours, objs);
	}

}
