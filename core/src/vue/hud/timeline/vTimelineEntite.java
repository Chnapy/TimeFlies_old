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
import static test.MainTest.MAX_HEIGHT;
import static test.MainTest.MAX_WIDTH;

/**
 * vTimelineEntite.java
 *
 */
public class vTimelineEntite extends Actor {

	private static final Texture TEXTURE = new Texture(Gdx.files.internal("timeline/fond_entite.png"));
	private static final int TEXTURE_WIDTH = MAX_WIDTH / 20;
	private static final int TEXTURE_HEIGHT = TEXTURE_WIDTH;
	private static final int TEXTURE_Y = MAX_HEIGHT - TEXTURE_HEIGHT - 30;
	private static final int TEXTURE_X_ECART = 50;

	private int texture_x;
	private float scale;

	/**
	 *
	 * @param ordreX représente l'ordre de l'entité dans la timeline, de 0 à N.
	 *               0 représente l'entité jouant son tour.
	 */
	public vTimelineEntite(int ordreX) {
		scale = (ordreX == 0) ? 1.20f : 1f;
		texture_x = ordreX * (TEXTURE_WIDTH + TEXTURE_X_ECART) + TEXTURE_X_ECART;
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE, texture_x + 25, TEXTURE_Y, TEXTURE_WIDTH * scale, TEXTURE_HEIGHT * scale);
	}

}
