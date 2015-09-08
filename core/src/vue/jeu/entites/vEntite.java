/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import gameplay.caracteristique.Orientation;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.entite.EtatEntite;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import vue.PackFrames;
import vue.jeu.map.vTuile;
import static vue.jeu.map.vTuile.TUILE_HEIGHT;

/**
 * vEntite.java
 * Représente la vue de l'entité
 * Gère les animations et déplacements.
 *
 */
public class vEntite extends Actor implements Observer {

	//Tailles du sprite de l'entité
	private static final int PERSO_WIDTH = 128;
	private static final int PERSO_HEIGHT = 192;

	/**
	 *
	 */
	private static final Animation[][] tabAnimations = {
		{
			new Animation(0.2f, PackFrames.getPackFrames("perso/perso1/stay/perso1_stay.atlas")),
			new Animation(0.1f, PackFrames.getPackFrames("perso/perso1/walk/perso1_walk.atlas"))
		},
		{
			new Animation(0.2f, PackFrames.getPackFrames("perso/perso2/stay/perso2_stay.atlas")),
			new Animation(0.1f, PackFrames.getPackFrames("perso/perso2/walk/perso2_walk.atlas"))
		}
	};

	static {
	}

	//Pour l'animation
	private float stateTime;
	private final int index;
	private int etat;	//0 = stay, 1 = walk

	public vEntite(final EntiteActive perso) {
		index = perso.getIndex();
		etat = 0;
		setSize(PERSO_WIDTH, PERSO_HEIGHT);
		setPos(perso.getCaracSpatiale().getPosition().x,
				perso.getCaracSpatiale().getPosition().y);

		debug();
	}

	@Override
	public void draw(Batch batch, float delta) {
		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(tabAnimations[index][etat].getKeyFrame(stateTime, true),
				getX(), getY(),
				getWidth(), getHeight()
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
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		Entite entite = (Entite) o;
		if (arg instanceof Object[] && entite.getEtatNow() == EtatEntite.DEPLACEMENT) {
			Object[] tabObjets = (Object[]) arg;
			int dureeAnim = (int) tabObjets[1];
			if (tabObjets[0] instanceof Point) {
				Point point = (Point) tabObjets[0];
//				System.out.println(point.x + " " + point.y);
				etat = 1;
				MoveToAction[] tabMoveTo = new MoveToAction[1];
				float[] position;
				for (int i = 0; i < 1; i++) {
					position = vTuile.getPosition(point.x, point.y);
					tabMoveTo[i] = Actions.moveTo(position[0] + PERSO_WIDTH / 2, position[1] + TUILE_HEIGHT / 2, (float) dureeAnim / 1000);
				}
				this.addAction(Actions.sequence(Actions.sequence(tabMoveTo), run(() -> {
					((EntiteActive) o).setEnDeplacement(false);
					etat = 0;
				})));
			} else if (tabObjets[0] instanceof Orientation) {
				//TODO Rotation
			}
		}
	}

	/**
	 * Applique les positions posX et posY.
	 * Selon le booléen, applique ou non les positions réelles.
	 *
	 * @param x
	 * @param y
	 * @param set_xy
	 */
	private void setPos(int x, int y) {
		float[] position = vTuile.getPosition(x, y);
		setX(position[0] + PERSO_WIDTH / 2);
		setY(position[1] + TUILE_HEIGHT / 2);
	}

}
