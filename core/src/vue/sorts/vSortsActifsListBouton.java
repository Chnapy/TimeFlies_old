/*
 * 
 * 
 * 
 */
package vue.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import static test.MainTest.MAX_WIDTH;

/**
 * vSortsActifsListBouton.java
 *
 */
public class vSortsActifsListBouton extends Table {

	private static final Texture TEXTURE_FOND = new Texture(Gdx.files.internal("sort/sort_fond.png"));
	private static final int TEXTURE_FOND_X = 500;
	private static final int TEXTURE_FOND_WIDTH = MAX_WIDTH - TEXTURE_FOND_X - 50;
	private static final int TEXTURE_FOND_HEIGHT = 64;
	private static final int TEXTURE_FOND_Y = 0;

	public vSortsActifsListBouton() {
		vSortsActifsBouton button = new vSortsActifsBouton();
		add(button).size(200, 200);
	}

}
