/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import gameplay.caracteristique.Carac;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import general.EtatGraphique;
import static general.EtatGraphique.STAY;
import static general.EtatGraphique.WALK;
import general.Mode;
import general.Orientation;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import vue.hud.bulle.BulleListener;
import vue.jeu.map.vTuile;
import static vue.jeu.map.vTuile.TUILE_HEIGHT;
import static vue.jeu.map.vTuile.TUILE_WIDTH;

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
	private static final AnimationManager[] tabManager = {
		new AnimationManager("perso1", 0.8f, 0.5f),
		new AnimationManager("perso2", 0.8f, 0.5f)
	};

	private final int index;
	private EtatGraphique etat;
	private Orientation orientation;

	public vEntite(final EntiteActive perso) {
		index = perso.getIndex();
		etat = STAY;
		orientation = perso.getCaracSpatiale().getOrientation();
		setSize(PERSO_WIDTH, PERSO_HEIGHT);
		setPos(perso.getCaracSpatiale().getPosition().x,
				perso.getCaracSpatiale().getPosition().y);

		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return perso.getNom() + " possede " + perso.getCaracPhysique().getCaracteristique(Carac.VITALITE).getActu() + " pdv.";
			}
		});
//		debug();
	}

	@Override
	public void draw(Batch batch, float delta) {
		batch.draw(tabManager[index].getFrame(etat, orientation, Gdx.graphics.getDeltaTime()), getX(), getY());
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
		orientation = entite.getCaracSpatiale().getOrientation();
//		System.out.println(orientation);
		if (arg instanceof Object[] && entite.getEtatNow() == Mode.DEPLACEMENT) {
			Object[] tabObjets = (Object[]) arg;
			int dureeAnim = (int) tabObjets[1];
			if (tabObjets[0] instanceof Point) {
				etat = WALK;
				Point point = (Point) tabObjets[0];
//				System.out.println(point.x + " " + point.y);
				MoveToAction[] tabMoveTo = new MoveToAction[1];
				float[] position;
				for (int i = 0; i < 1; i++) {
					position = vTuile.getPosition(point.x, point.y);
					tabMoveTo[i] = Actions.moveTo(position[0] + (TUILE_WIDTH - PERSO_WIDTH) / 2, position[1] + TUILE_HEIGHT / 4, (float) dureeAnim / 1000);
				}
				this.addAction(Actions.sequence(Actions.sequence(tabMoveTo), run(() -> {
					((EntiteActive) o).setEnDeplacement(false);
					etat = STAY;
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
		setX(position[0] + (TUILE_WIDTH - PERSO_WIDTH) / 2);
		setY(position[1] + TUILE_HEIGHT / 4);
	}

}
