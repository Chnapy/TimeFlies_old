/*
 * 
 * 
 * 
 */
package vue.sorts;

import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Observable;
import java.util.Observer;
import test.MainTest;

/**
 * vSortsPassifs.java
 *
 */
public class vSortsPassifs extends Stage implements Observer {

	public vSortsPassifs() {
	}

	public void render() {
		setViewport(MainTest.viewport);
		act();
		draw();
	}

	@Override
	public void update(Observable o, Object arg) {
	}

}
