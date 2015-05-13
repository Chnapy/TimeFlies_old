/*
 * 
 * 
 * 
 */
package vue.hud.timeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;
import static test.MainTest.MAX_HEIGHT;

/**
 * vTimeline.java
 *
 */
public class vTimeline extends Group {

	//Texture de fond
	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("timeline/barre_timeline.png"));

	//Position et taille de la timeline
	private static final int X = 50;
	private static final int WIDTH = 1820;
	private static final int HEIGHT = 92;
	private static final int Y = MAX_HEIGHT - HEIGHT - 12;

	//Vue du temps d'action
	private final vTimelineTempsAction vtemps;

	//Vue des entités sur la timeline
	private final Array<vTimelineEntite> listEntite;

	public vTimeline(final Array<? extends EntiteActive> listEntites) {
		TEXTURE_FOND.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		listEntite = new Array<vTimelineEntite>();
		addActor(new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(TEXTURE_FOND, 0, 0, WIDTH, HEIGHT);
			}
		});
		vtemps = new vTimelineTempsAction();
		listEntites.forEach((entite) -> {
			((EntiteActive) entite).addObserver(vtemps);
		});
		addActor(vtemps);
		vTimelineEntite temp2;
		for (int i = 0; i < listEntites.size; i++) {
			temp2 = new vTimelineEntite(listEntites.get(i), i);
			addActor(temp2);
			listEntite.add(temp2);
		}
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

}
