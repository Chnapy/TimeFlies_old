/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.Personnage;
import test.MainTest;

/**
 * vEntites.java
 *
 */
public class vEntites extends Stage {

	public vEntites(Array<Personnage> personnages) {
		personnages.forEach(perso -> {
			vEntite vent = new vEntite(perso);
			perso.addObserver(vent);
			getRoot().addActor(vent);
		});
		setViewport(MainTest.viewport);
	}

	public void render() {
		act();
		draw();
	}

}
