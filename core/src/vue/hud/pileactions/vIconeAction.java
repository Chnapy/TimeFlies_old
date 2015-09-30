/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * vIconeAction.java
 *
 */
public class vIconeAction extends Actor {

	//Tableau de textures des actions
	private static final String[] PATH = {
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png",
		"sort/sort_fond.png"
	};

	private final Texture texture;

	public vIconeAction(AssetManager manager, int indexTexture) {
		texture = manager.get(PATH[indexTexture]);
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}

}
