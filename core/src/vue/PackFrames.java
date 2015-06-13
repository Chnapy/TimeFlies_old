/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * PackFrames.java
 *
 */
public class PackFrames {

	public static TextureRegion[] getPackFrames(String pathAtlas) {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(pathAtlas));
		Array<AtlasRegion> array = atlas.getRegions();
		array.forEach((texture) -> {
			texture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		});
		return array.toArray(TextureRegion.class);
	}

}
