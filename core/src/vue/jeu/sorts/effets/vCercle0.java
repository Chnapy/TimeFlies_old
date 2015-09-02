/*
 * 
 * 
 * 
 */
package vue.jeu.sorts.effets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;

/**
 * vCercle0.java
 *
 */
public class vCercle0 extends vEffet {

	private final static TextureRegion SPRITE = new TextureRegion(new Texture(Gdx.files.internal("sort_fx/SortQuiFaitMal_0/cercle.png")));

	public vCercle0() {
		super(0, 90, new Point(64, 256));
	}

	@Override
	protected void start() {
		width = 128;
		height = width;
	}

	@Override
	public void render(Batch batch, float parentAlpha) {
		if (delta * 1000 < dureeReelle / 3) {
			batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, Math.min(delta * 3, 1));
		} else if (delta * 1000 > dureeReelle / 3 * 2) {
			batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, Math.max((dureeReelle - delta * 1000) / (dureeReelle / 3), 0));
		}
		batch.draw(SPRITE, positionStart.x + position.x, positionStart.y + position.y, 64, 64, width, height, 1, 1, delta * 500);
		batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 1);
	}

}