/*
 * 
 * 
 * 
 */
package vue.hud.timeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import gameplay.core.Timeline;
import java.util.Observable;
import java.util.Observer;
import static test.MainTest.MAX_HEIGHT;
import static test.MainTest.MAX_WIDTH;

/**
 * vTimeline.java
 *
 */
public class vTimeline extends Group {

	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("timeline/timeline_fond.png"));
	private static final int X = 50;
	private static final int WIDTH = MAX_WIDTH - X * 2;
	private static final int HEIGHT = 64;
	private static final int Y = MAX_HEIGHT - HEIGHT;

	public vTimeline(final Timeline timeline) {
		TEXTURE_FOND.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		this.addActor(new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(TEXTURE_FOND, 0, 0, WIDTH, HEIGHT);
			}
		});
		for (int i = 0; i < 5; i++) {
			addActor(new vTimelineEntite(i));
		}
	}

}
