/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import gameplay.map.Tuile;

/**
 * vMap.java
 *
 */
public class vMap {

	public static final int TUILE_WIDTH = 128;
	public static final int TUILE_HEIGHT = 64;

	/**
	 * Récupération des tuiles :
	 *
	 * tuileSimple
	 * tuileTrou
	 * tuileObstacle
	 * tuileEcran
	 */
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("tuile/tuile.atlas"));
	private static final TextureRegion[] tabTexture = {
		atlas.findRegion("tuileSimple"),
		atlas.findRegion("tuileTrou"),
		atlas.findRegion("tuileObstacle"),
		atlas.findRegion("tuileEcran")
	};
	private final SpriteBatch batch;

	private Tuile[][] tabTuiles;

	/**
	 *
	 * @param tabTuiles
	 */
	public vMap(final Tuile[][] tabTuiles) {
		this.tabTuiles = tabTuiles;

		batch = new SpriteBatch();
		for (TextureRegion texture : tabTexture) {
			texture.setRegionWidth(TUILE_WIDTH);
			texture.setRegionHeight(TUILE_HEIGHT);
		}
	}

	public void render() {

		int t;

		batch.begin();
		for (int i = 0; i < tabTuiles.length; i++) {
			for (int j = 0; j < tabTuiles[0].length; j++) {
				switch (tabTuiles[i][j].getEtat()) {
					case SIMPLE:
						t = 0;
						break;
					case TROU:
						t = 1;
						break;
					case OBSTACLE:
						t = 2;
						break;
					case ECRAN:
						t = 3;
						break;
					default:
						throw new Error("Tuile non gérée");
				}
				batch.draw(tabTexture[t], 500 + j * TUILE_WIDTH / 2 + i * -TUILE_HEIGHT, 300 + j * -TUILE_WIDTH / 4 + i * -TUILE_HEIGHT / 2);
			}
		}
		batch.end();
	}

}
