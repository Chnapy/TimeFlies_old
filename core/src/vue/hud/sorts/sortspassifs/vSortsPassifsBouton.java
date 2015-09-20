/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import gameplay.sort.SortPassif;
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

	public vSortsPassifsBouton(SortPassif sort, int index) {
		super(sort, TEXTURES[index]);
	}

	public static final void filterTexture() {
	}

}
