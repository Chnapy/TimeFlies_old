/*
 * 
 * 
 * 
 */
package vue.jeu;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;
import test.MainTest;
import vue.jeu.map.vMap;
import vue.jeu.entites.vEntites;

/**
 * vJeu.java
 * 
 */
public class vJeu extends Stage {

	private final cCombat combat;
	private final vMap vmap;
	private final vEntites ventites;
	
	public vJeu(final cCombat ccombat, final Tuile[][] tabTuiles, final Array<Personnage> personnages) {
		combat = ccombat;
		vmap = new vMap(ccombat, tabTuiles);
		ventites = new vEntites(personnages);
		
		addActor(vmap);
		addActor(ventites);
	}
	
	public void render() {
		setViewport(MainTest.viewport);
		act();
		draw();
		
	}
	
	public void nouveauTour(EntiteActive entite) {
//		vhud.nouveauTour(entite.getTabSortActif(), entite.getTabSortPassif());
	}

	public cCombat getCombat() {
		return combat;
	}

	public vMap getVmap() {
		return vmap;
	}

	public vEntites getVentites() {
		return ventites;
	}

}
