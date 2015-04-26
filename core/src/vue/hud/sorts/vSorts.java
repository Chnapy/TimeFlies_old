/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
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
		addBarre(barreSortsActifs);
		addBarre(barreSortsPassifs);
		
		vSortsBouton.init();
	}
	
	private void addBarre(Actor barre) {
		addActor(barre);
	}
	
	public void nouveauTour(SortActif[] sactifs, SortPassif[] spassifs) {
		barreSortsActifs.nouveauTour(sactifs);
		barreSortsPassifs.nouveauTour(spassifs);
	}
	
	public void finTour() {
		barreSortsActifs.finTour();
		barreSortsPassifs.finTour();
	}

}
