/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import vue.hud.sorts.vSortsBouton;

/**
 * vSortsActifsBouton.java
 *
 */
public class vSortsPassifsBouton extends vSortsBouton {

	//tableau des textures
	private static final Texture[] TEXTURES = {
		new Texture(Gdx.files.internal("sort/sort_fond.png")),
		new Texture(Gdx.files.internal("sort/sort_fond.png"))
	};

	static {
		for (Texture texture : TEXTURES) {
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	public vSortsPassifsBouton(int index) {
		super(TEXTURES[index]);
	}

	public static final void filterTexture() {
	}

	@Override
	protected void drawIcon(Batch batch, float parentAlpha) {
	}

}
