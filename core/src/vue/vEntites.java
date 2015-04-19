/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.Personnage;

/**
 * vEntites.java
 *
 */
public class vEntites {

	private Array<vEntite> listEntites;

	public vEntites(Array<Personnage> personnages) {
		listEntites = new Array<vEntite>();
		personnages.forEach(perso -> {
			vEntite vent = new vEntite(perso);
			perso.addObserver(vent);
			listEntites.add(vent);
		});
	}

	public void render() {
		listEntites.forEach(entite -> entite.render());
	}

}
