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
import static vue.vMap.TUILE_HEIGHT;
import static vue.vMap.TUILE_WIDTH;

/**
 * vEntite.java
 * Représente la vue de l'entité
 *
 */
public class vEntite implements Observer {

	private static final int PERSO_WIDTH = 64;
	private static final int PERSO_HEIGHT = 92;

	/**
	 * Nom du perso -> nom de fichier
	 */
	private static final String[][] tabEntiteSprite = {
		{"Guerrier", "perso"}
	};

	private Texture texture = null;
	private SpriteBatch batch;

	private int posX;
	private int posY;

	public vEntite(final Personnage perso) {
		for (String[] tabEntiteSprite1 : tabEntiteSprite) {
			if (tabEntiteSprite1[0].equals(perso.getNom())) {
				texture = new Texture("perso/" + tabEntiteSprite1[1] + ".png");
			}
		}
		if (texture == null) {
			throw new Error("Perso non géré : " + perso.getNom());
		}
		posX = perso.getCaracSpatiale().getPosition().x;
		posY = perso.getCaracSpatiale().getPosition().y;

		batch = new SpriteBatch();
	}

	public void render() {
		batch.begin();
		batch.draw(texture,
				500 + posX * TUILE_WIDTH / 2 + posY * -TUILE_HEIGHT - PERSO_WIDTH / 2,
				300 + posX * -TUILE_WIDTH / 4 + posY * -TUILE_HEIGHT / 2,
				PERSO_WIDTH,
				PERSO_HEIGHT);
		batch.end();
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
