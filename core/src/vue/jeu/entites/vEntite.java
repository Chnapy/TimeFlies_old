/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import controleur.ControleurPrincipal;
import gameplay.caracteristique.Carac;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import general.EtatGraphique;
import static general.EtatGraphique.STAY;
import static general.EtatGraphique.WALK;
import general.Mode;
import general.Orientation;
import general.Tourable;
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
public class vEntite extends Group implements Observer, Tourable {

	//Tailles du sprite de l'entité
	private static final int PERSO_WIDTH = 128;
	private static final int PERSO_HEIGHT = 192;
	private static final String[] tabManager = {
		"perso1",
		"perso2"
	};

	private final AnimationManager amanager;
	private final EntiteHud hud;

	private EtatGraphique etat;
	private Orientation orientation;

	public vEntite(final EntiteActive entite) {
		amanager = new AnimationManager(tabManager[entite.getIndex()], 0.8f, 0.5f);
		etat = STAY;
		orientation = entite.getCaracSpatiale().getOrientation();
		setSize(PERSO_WIDTH, PERSO_HEIGHT);
		setPos(entite.getCaracSpatiale().getPosition().x,
				entite.getCaracSpatiale().getPosition().y);

		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return entite.getNom() + " possede " + entite.getCaracPhysique().getCaracteristique(Carac.VITALITE).getActu() + " pdv.";
			}
		});

		hud = new EntiteHud(entite, PERSO_WIDTH, PERSO_HEIGHT);
		addActor(hud);
//		debugAll();
	}

	@Override
	public void draw(Batch batch, float delta) {
		batch.draw(amanager.getFrame(etat, orientation, Gdx.graphics.getDeltaTime()), getX(), getY());
		super.draw(batch, delta);
	}

	/**
	 * Observer de l'entité (gameplay).
	 * Recois le chemin à parcourir pour l'entité.
	 * Effectue les actions de déplacement.
	 *
	 * @param o	  Entite
	 * @param arg	Array de Point
	 */
	@SuppressWarnings("unchecked")	//Enleve le warning lors de la compilation
	@Override
	public void update(Observable o, Object arg) {
		Entite entite = (Entite) o;
		orientation = entite.getCaracSpatiale().getOrientation();
		if (arg instanceof Object[] && entite.getEtatNow() == Mode.DEPLACEMENT) {
			Object[] tabObjets = (Object[]) arg;
			int dureeAnim = (int) tabObjets[1];
			if (tabObjets[0] instanceof Point) {
				etat = WALK;
				Point point = (Point) tabObjets[0];
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
	 *
	 * @param x
	 * @param y
	 */
	private final void setPos(int x, int y) {
		float[] position = vTuile.getPosition(x, y);
		setX(position[0] + (TUILE_WIDTH - PERSO_WIDTH) / 2);
		setY(position[1] + TUILE_HEIGHT / 4);
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		hud.nouveauTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		hud.finTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		hud.enTour(controleur, entiteEnCours, objs);
	}

}
