/*
 * 
 * 
 * 
 */
package vue.timeline;

import com.badlogic.gdx.scenes.scene2d.Stage;
import gameplay.core.Timeline;
import java.util.Observable;
import java.util.Observer;

/**
 * vTimeline.java
 *
 */
public class vTimeline extends Stage implements Observer {


	public vTimeline(final Timeline timeline) {
		addActor(new vTimelineFond());
		for(int i = 0; i < 5; i++) {
		addActor(new vTimelineEntite(i));
		}
	}

	public void render() {
		act();
		draw();
	}

	@Override
	public void update(Observable o, Object arg) {
	}

}