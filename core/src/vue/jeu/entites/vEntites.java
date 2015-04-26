/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.Personnage;

/**
 * vEntites.java
 *
 */
public class vEntites extends Group {

	public vEntites(Array<Personnage> personnages) {
		personnages.forEach(perso -> {
			vEntite vent = new vEntite(perso);
			perso.addObserver(vent);
			addActor(vent);
		});
	}

}
