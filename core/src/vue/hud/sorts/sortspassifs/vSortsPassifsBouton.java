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

	private static final Texture[] TEXTURES = {
		new Texture(Gdx.files.internal("sort/sort_fond.png")),
	};
	
	public vSortsPassifsBouton(int index) {
		super(TEXTURES[index]);
	}
	
	public static final void filterTexture() {
		filterTexture(TEXTURES);
	}

	@Override
	protected void drawIcon(Batch batch, float parentAlpha) {
	}

}
