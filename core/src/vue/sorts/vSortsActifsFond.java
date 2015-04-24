/*
 * 
 * 
 * 
 */
package vue.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static test.MainTest.MAX_WIDTH;

/**
 * vSortsActifsFond.java
 * 
 */
public class vSortsActifsFond extends Actor {

	private static final Texture TEXTURE = new Texture(Gdx.files.internal("sort/sort_fond.png"));
	private static final int TEXTURE_X = 500;
	private static final int TEXTURE_WIDTH = MAX_WIDTH - TEXTURE_X - 50;
	private static final int TEXTURE_HEIGHT = 64;
	private static final int TEXTURE_Y = 0;

	public vSortsActifsFond() {
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE, TEXTURE_X, TEXTURE_Y, TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

}
