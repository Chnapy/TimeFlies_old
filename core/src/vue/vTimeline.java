/*
 * 
 * 
 * 
 */
package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import gameplay.core.Timeline;
import java.util.Observable;
import java.util.Observer;

/**
 * vTimeline.java
 * 
 */
public class vTimeline extends Stage implements Observer {
	
	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("timeline/timeline_fond.png"));
	private static final int TEXTURE_FOND_X = 0;
	private static final int TEXTURE_FOND_Y = 0;
	private static final int TEXTURE_FOND_WIDTH = 500;
	private static final int TEXTURE_FOND_HEIGHT = 32;
	
//	private SpriteBatch batch;
	
	public vTimeline(final Timeline timeline) {
//		batch = new SpriteBatch();
	}
	
	public void render() {
//		batch.begin();
//		//Fond
//		batch.draw(TEXTURE_FOND, TEXTURE_FOND_X, TEXTURE_FOND_Y, TEXTURE_FOND_WIDTH, TEXTURE_FOND_HEIGHT);
//		batch.end();
		
		act();
		draw();
	}

	@Override
	public void update(Observable o, Object arg) {
//		System.out.println("");
	}

}
