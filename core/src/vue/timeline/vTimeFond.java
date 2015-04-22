/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.timeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Stage
 */
public class vTimeFond extends Actor {

	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("timeline/timeline_fond.png"));
	private static final int TEXTURE_FOND_WIDTH = 1920 - 100;
	private static final int TEXTURE_FOND_HEIGHT = 32;
	private static final int TEXTURE_FOND_X = 50;
	private static final int TEXTURE_FOND_Y = Gdx.graphics.getHeight() - TEXTURE_FOND_HEIGHT;

	public vTimeFond() {
		System.out.println(Gdx.graphics.getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE_FOND, TEXTURE_FOND_X, TEXTURE_FOND_Y,
				TEXTURE_FOND_WIDTH, TEXTURE_FOND_HEIGHT);
	}

}
