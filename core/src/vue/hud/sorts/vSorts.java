/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.scenes.scene2d.Group;
import controleur.cCombat;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import vue.hud.sorts.sortsactifs.vBarreSortsActifs;
import vue.hud.sorts.sortspassifs.vBarreSortsPassifs;

/**
 * vSorts.java
 *
 */
public class vSorts extends Group {

	private final vBarreSortsActifs barreSortsActifs;
	private final vBarreSortsPassifs barreSortsPassifs;

	public vSorts() {
		barreSortsActifs = new vBarreSortsActifs();
		barreSortsPassifs = new vBarreSortsPassifs();
		addActor(barreSortsActifs);
		addActor(barreSortsPassifs);
	}

	public void nouveauTour(cCombat ccombat, SortActif[] sactifs, SortPassif[] spassifs) {
		barreSortsActifs.nouveauTour(ccombat, sactifs);
		barreSortsPassifs.nouveauTour(ccombat, spassifs);
	}

	public void finTour() {
		barreSortsActifs.finTour();
		barreSortsPassifs.finTour();
	}

}
