/*
 * 
 * 
 * 
 */
package vue.timeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static test.MainTest.camera;

/**
 * vTimelineFond.java
 *
 */
public class vTimelineFond extends Actor {

	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("timeline/timeline_fond.png"));
	private static final int TEXTURE_FOND_X = 50;
	private static final int TEXTURE_FOND_WIDTH = 1920 - TEXTURE_FOND_X * 2;
	private static final int TEXTURE_FOND_HEIGHT = 64;
	private static final int TEXTURE_FOND_Y = 1080 - TEXTURE_FOND_HEIGHT;

	public vTimelineFond() {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setProjectionMatrix(camera.combined);
		batch.draw(TEXTURE_FOND, TEXTURE_FOND_X, TEXTURE_FOND_Y, TEXTURE_FOND_WIDTH, TEXTURE_FOND_HEIGHT);
	}

}
