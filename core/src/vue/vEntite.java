/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;
import gameplay.entite.EntiteActive;
import java.util.Observable;
import java.util.Observer;
import static vue.map.vTuile.OFFSET_X;
import static vue.map.vTuile.OFFSET_Y;
import static vue.map.vTuile.TUILE_HEIGHT;
import static vue.map.vTuile.TUILE_WIDTH;

/**
 * vEntite.java
 * Représente la vue de l'entité
 *
 */
public class vEntite extends Actor implements Observer {

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

	public vEntite(final EntiteActive perso) {
		for (String[] tabEntiteSprite1 : tabEntiteSprite) {
			if (tabEntiteSprite1[0].equals(perso.getNom())) {
				texture = new Texture("perso/" + tabEntiteSprite1[1] + ".png");
			}
		}
		if (texture == null) {
			throw new Error("Perso non géré : " + perso.getNom());
		}
		setPosition(perso.getCaracSpatiale().getPosition().x,
				posY = perso.getCaracSpatiale().getPosition().y, true);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture,
				getX(), getY(),
				PERSO_WIDTH, PERSO_HEIGHT
		);
	}

	/**
	 * Observer de l'entité (gameplay)
	 *
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		setPosition(((EntiteActive) o).getCaracSpatiale().getPosition().x,
				((EntiteActive) o).getCaracSpatiale().getPosition().y, false);
		float[] position = getPosition(posX, posY);
		this.addAction(Actions.moveTo(position[0], position[1], 1));
	}

	private void setPosition(int x, int y, boolean set_xy) {
		posX = x;
		posY = y;
		if (set_xy) {
			float[] position = getPosition(x, y);
			setX(position[0]);
			setY(position[1]);
		}
	}

	private float[] getPosition(int x, int y) {
		return new float[]{
			OFFSET_X + x * TUILE_WIDTH / 2 + (y - 1) * -TUILE_HEIGHT - PERSO_WIDTH / 2,
			OFFSET_Y + x * -TUILE_WIDTH / 4 + (y - 1) * -TUILE_HEIGHT / 2
		};
	}

}
