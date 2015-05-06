/*
 * 
 * 
 * 
 */
package vue.jeu;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.entite.Personnage;
import gameplay.map.Tuile;
import test.MainTest;
import vue.jeu.entites.vEntites;
import vue.jeu.map.vMap;

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

	public void nouveauTour() {

	}
	
	public void finTour() {
		vmap.finTour();
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
