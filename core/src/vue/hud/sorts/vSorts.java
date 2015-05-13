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

	//Vue des sorts actifs
	private final vBarreSortsActifs barreSortsActifs;

	//Vue des sorts passifs
	private final vBarreSortsPassifs barreSortsPassifs;

	public vSorts() {
		barreSortsActifs = new vBarreSortsActifs();
		barreSortsPassifs = new vBarreSortsPassifs();
		addActor(barreSortsActifs);
		addActor(barreSortsPassifs);
	}

	/**
	 * Nouveau tour d'une entité
	 *
	 * @param ccombat
	 * @param sactifs
	 * @param spassifs
	 */
	public void nouveauTour(cCombat ccombat, SortActif[] sactifs, SortPassif[] spassifs) {
		barreSortsActifs.nouveauTour(ccombat, sactifs);
		barreSortsPassifs.nouveauTour(ccombat, spassifs);
	}

	/**
	 * Fin tour d'une entité
	 */
	public void finTour() {
		barreSortsActifs.finTour();
		barreSortsPassifs.finTour();
	}

}
