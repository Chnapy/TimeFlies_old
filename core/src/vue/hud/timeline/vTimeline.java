/*
 * 
 * 
 * 
 */
package vue.hud.timeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;
import static test.MainTest.MAX_HEIGHT;
import vue.Couleur;
import vue.hud.vHud;

/**
 * vTimeline.java
 *
 */
public class vTimeline extends Group {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "timeline");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "timeline");

	//Position et taille de la timeline
	private static final int X = 50;
	private static final int WIDTH = 1820;
	private static final int HEIGHT = 92;
	private static final int Y = MAX_HEIGHT - HEIGHT - 12;

	private final ShapeRenderer shapeRenderer;

	//Vue du temps d'action
	private final vTimelineTempsAction vtemps;

	//Vue des entités sur la timeline
	private final Array<vTimelineEntite> listEntite;

	public vTimeline(final Array<? extends EntiteActive> listEntites) {
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		listEntite = new Array<vTimelineEntite>();
		shapeRenderer = new ShapeRenderer();
		vtemps = new vTimelineTempsAction();
//		addActor(vtemps);	//Jauge horizontale temps action
		vTimelineEntite temp;
		for (int i = 0; i < listEntites.size; i++) {
			temp = new vTimelineEntite(listEntites.get(i), i);
			addActor(temp);
			listEntite.add(temp);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		vHud.drawBackground(X, Y, getWidth(), getHeight(), FOND_COULEUR, FOND_CONTOUR_COULEUR);

		//Batch
		batch.begin();
		super.draw(batch, parentAlpha);
	}

	/**
	 * Nouveau tour d'une entité
	 */
	public void nouveauTour() {
		listEntite.forEach((entite) -> {
			entite.nouveauTour(listEntite.size - 1);
		});
		vtemps.setScale(1);
	}

	public void tourEnCours(EntiteActive entiteEnCours) {
		vtemps.tourEnCours(entiteEnCours);
	}

}
