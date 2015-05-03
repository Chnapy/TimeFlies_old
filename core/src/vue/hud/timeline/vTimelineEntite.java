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
import gameplay.entite.EntiteActive;

/**
 * vTimelineEntite.java
 *
 */
public class vTimelineEntite extends Actor {

	private static final Texture[] TIMELINE_PORTRAIT = {
		new Texture(Gdx.files.internal("timeline/perso.png")),
		new Texture(Gdx.files.internal("timeline/perso2.png"))
	};

	private static final Texture TEXTURE = new Texture(Gdx.files.internal("timeline/fond_entite.png"));
	private static final int TEXTURE_WIDTH = 75;
	private static final int TEXTURE_HEIGHT = TEXTURE_WIDTH;
	private static final int TEXTURE_Y = 10;
	private static final int TEXTURE_X_ECART = 50;

	static {
		for (Texture texture : TIMELINE_PORTRAIT) {
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	private int TEXTURE_X;
	private final int indexTexture;
	private int ordre;
	private float scale;

	/**
	 *
	 * @param entite
	 * @param ordreX représente l'ordre de l'entité dans la timeline, de 0 à N.
	 *               0 représente l'entité jouant son tour.
	 */
	public vTimelineEntite(EntiteActive entite, int ordreX) {
		ordre = ordreX;
		setScale();
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		indexTexture = entite.getIndexTextureTimeline();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE, TEXTURE_X, TEXTURE_Y, TEXTURE_WIDTH * scale, TEXTURE_HEIGHT * scale);
		batch.draw(TIMELINE_PORTRAIT[indexTexture], TEXTURE_X + 5, TEXTURE_Y + 5, TEXTURE_WIDTH * scale - 10, TEXTURE_HEIGHT * scale - 10);
	}

	public void nouveauTour(int ordreMax) {
		ordre = (ordre == 0) ? ordreMax : ordre - 1;
		setScale();
	}

	private void setScale() {
		scale = (ordre == 0) ? 1.20f : 1f;
		TEXTURE_X = ordre * (TEXTURE_WIDTH + TEXTURE_X_ECART) + TEXTURE_X_ECART;
	}

}
