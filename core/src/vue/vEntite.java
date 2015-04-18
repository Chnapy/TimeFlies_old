/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gameplay.entite.Personnage;
import java.util.Observable;
import java.util.Observer;

/**
 * vEntite.java
 * Représente la vue de l'entité
 *
 */
public class vEntite implements Observer {

	/**
	 * Nom du perso -> nom de fichier
	 */
	private static final String[][] tabEntiteSprite = {
		{"Guerrier", "perso"}
	};

	private Texture texture = null;
	private SpriteBatch batch;

	public vEntite(final Personnage perso) {
		for (String[] tabEntiteSprite1 : tabEntiteSprite) {
			if (tabEntiteSprite1[0].equals(perso.getNom())) {
				texture = new Texture("perso/" + tabEntiteSprite1[1] + ".png");
			}
		}
		if(texture == null) {
			throw new Error("Perso non géré");
		}
	}

	public void render() {

	}

	/**
	 * Observer de l'entité (gameplay)
	 * 
	 * @param o
	 * @param arg 
	 */
	@Override
	public void update(Observable o, Object arg) {

	}

}
