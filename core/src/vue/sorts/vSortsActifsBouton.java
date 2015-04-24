/*
 * 
 * 
 * 
 */
package vue.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * vSortsActifsBouton.java
 * 
 */
public class vSortsActifsBouton extends Button {

	private static final Texture TEXTURE = new Texture(Gdx.files.internal("sort/sort.png"));
	private final int TEXTURE_X;
	private static final int TEXTURE_WIDTH = 64;
	private static final int TEXTURE_HEIGHT = 64;
	private static final int TEXTURE_Y = 32;

	public vSortsActifsBouton(final int x) {
		TEXTURE_X = x;
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.validate();
		batch.draw(TEXTURE, TEXTURE_X, TEXTURE_Y, TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

}
