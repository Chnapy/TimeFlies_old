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

/**
 * vSortsBouton.java
 *
 */
public abstract class vSortsBouton extends Actor {

	//Texture de fond du sort
	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("sort/sort_fond.png"));

	//Taille de la texture
	private static final int TEXTURE_WIDTH = 64;
	private static final int TEXTURE_HEIGHT = 64;

//	private static final Texture[] ICONES;
	static {
		TEXTURE_FOND.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	//Texture du sort
	private final Texture TEXTURE;

	//Index de la texture
	private int index;

	public vSortsBouton(Texture texture) {
		TEXTURE = texture;
		setSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE_FOND, getX(), getY(), getWidth(), getHeight());
		batch.draw(TEXTURE, getX(), getY(), getWidth(), getHeight());
		drawIcon(batch, parentAlpha);
	}

	//Affichage des icones
	protected abstract void drawIcon(Batch batch, float parentAlpha);

}
