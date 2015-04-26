/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static vue.hud.vHud.FONT;

/**
 * vSortsBouton.java
 *
 */
public abstract class vSortsBouton extends Actor {

	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("sort/sort_fond.png"));
	private static final int TEXTURE_WIDTH = 64;
	private static final int TEXTURE_HEIGHT = 64;
	
//	private static final Texture[] ICONES;

	private final Texture TEXTURE;
	
	private int index;

	public vSortsBouton(Texture texture) {
		TEXTURE = texture;
		setSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}
	
	public static final void init() {
		TEXTURE_FOND.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE_FOND, getX(), getY(), getWidth(), getHeight());
		batch.draw(TEXTURE, getX(), getY(), getWidth(), getHeight());
		drawIcon(batch, parentAlpha);
	}
	
	public static void filterTexture(Texture[] tabTexture) {
		for(Texture texture : tabTexture) {
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	protected abstract void drawIcon(Batch batch, float parentAlpha);

}
