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
import static test.MainTest.MAX_WIDTH;

/**
 * vSortsActifsBouton.java
 * 
 */
public class vSortsActifsBouton extends Button {

	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("sort/sort_fond.png"));
	private static final int TEXTURE_FOND_X = 500;
	private static final int TEXTURE_FOND_WIDTH = MAX_WIDTH - TEXTURE_FOND_X - 50;
	private static final int TEXTURE_FOND_HEIGHT = 64;
	private static final int TEXTURE_FOND_Y = 0;

	public vSortsActifsBouton() {
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.validate();
		batch.draw(TEXTURE_FOND, TEXTURE_FOND_X, TEXTURE_FOND_Y, TEXTURE_FOND_WIDTH, TEXTURE_FOND_HEIGHT);
	}

}
