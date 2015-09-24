/*
 * 
 * 
 * 
 */
package vue.jeu.sorts.effets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;

/**
 * vCercle1.java
 *
 */
public class vCercle1 extends vEffet {

	private static final String PATH = "sort_fx/SortQuiFaitMal_0/cercle.png";
	
	private final TextureRegion SPRITE;
	private int difX;
	private int difY;
	private final int precision;

	public vCercle1(AssetManager manager, int start, int duree, int precision) {
		super(manager, start, duree, new Point(128, 256));
		this.precision = precision;
		SPRITE = new TextureRegion((Texture) manager.get(PATH));
	}

	@Override
	protected void start() {
		width = 64;
		height = width;
		difX = positionEnd.x + (int) (Math.random() > 0.5 ? precision * Math.random() : -precision * Math.random()) - positionStart.x;
		difY = positionEnd.y + (int) (Math.random() > 0.5 ? precision * Math.random() : -precision * Math.random()) - positionStart.y - position.y;
	}

	@Override
	public void render(Batch batch, float parentAlpha) {
		batch.draw(SPRITE,
				positionStart.x + position.x + delta * (difX / (dureeReelle / 1000)),
				positionStart.y + position.y + delta * (difY / (dureeReelle / 1000)),
				32, 32, width, height, 1, 1, delta * 1000);
	}

}
