/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import vue.map.vTuile;
import static vue.map.vTuile.TUILE_HEIGHT;

/**
 * vEntite.java
 * Représente la vue de l'entité
 * Gère les animations et déplacements.
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
	private boolean enDeplacement;

	private int posX;
	private int posY;

	public vEntite(final EntiteActive perso) {
		for (String[] tabEntiteSprite1 : tabEntiteSprite) {
			if (tabEntiteSprite1[0].equals(perso.getNom())) {
				texture = new Texture("perso/" + tabEntiteSprite1[1] + ".png");
				break;
			}
		}
		if (texture == null) {
			throw new Error("Perso non géré : " + perso.getNom());
		}
		setPosition(perso.getCaracSpatiale().getPosition().x,
				perso.getCaracSpatiale().getPosition().y, true);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture,
				getX(), getY(),
				PERSO_WIDTH, PERSO_HEIGHT
		);
	}

	/**
	 * Observer de l'entité (gameplay).
	 * Recois le chemin à parcourir pour l'entité.
	 * Effectue les actions de déplacement.
	 *
	 * @param o	  Entite
	 * @param arg	Array de Point
	 */
	@Override
	public void update(Observable o, Object arg) {
		Array<Point> listParcours = (Array<Point>) arg;
		setPosition(((Entite) o).getCaracSpatiale().getPosition().x,
				((Entite) o).getCaracSpatiale().getPosition().y, false);

		MoveToAction[] tabMoveTo = new MoveToAction[listParcours.size];
		float[] position;
		for (int i = 0; i < listParcours.size; i++) {
			position = vTuile.getPosition(listParcours.get(i).x, listParcours.get(i).y);
			tabMoveTo[i] = Actions.moveTo(position[0] + PERSO_WIDTH / 2, position[1] + TUILE_HEIGHT / 2, 0.5f);
		}
		this.addAction(Actions.sequence(Actions.sequence(tabMoveTo), run(() -> {
			((EntiteActive)o).setEnDeplacement(false);
		})));
	}

	/**
	 * Applique les positions posX et posY.
	 * Selon le booléen, applique ou non les positions réelles.
	 *
	 * @param x
	 * @param y
	 * @param set_xy
	 */
	private void setPosition(int x, int y, boolean set_xy) {
		posX = x;
		posY = y;
		if (set_xy) {
			float[] position = vTuile.getPosition(x, y);
			setX(position[0] + PERSO_WIDTH / 2);
			setY(position[1] + TUILE_HEIGHT / 2);
		}
	}

}
