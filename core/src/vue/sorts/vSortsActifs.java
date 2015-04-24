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
 * vSortsActifs.java
 *
 */
public class vSortsActifs extends Stage implements Observer {

	private final vSortsActifsListBouton listBoutons = new vSortsActifsListBouton();

	public vSortsActifs() {
		listBoutons.setFillParent(true);
		addActor(new vSortsActifsFond());
		addActor(listBoutons);
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
