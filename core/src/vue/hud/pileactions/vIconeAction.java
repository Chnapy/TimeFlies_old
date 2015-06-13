/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * vIconeAction.java
 *
 */
public class vIconeAction extends Actor {

	//Tableau de textures des actions
	private static final Texture[] TEXTURES = {
		new Texture(Gdx.files.internal("sort/sort_fond.png")),
		new Texture(Gdx.files.internal("sort/sort_fond.png")),
		new Texture(Gdx.files.internal("sort/sort_fond.png"))
	};

	static {
		for (Texture texture : TEXTURES) {
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	private final int index;

	public vIconeAction(int indexTexture) {
		index = indexTexture;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURES[index], getX(), getY(), getWidth(), getHeight());
	}

}
