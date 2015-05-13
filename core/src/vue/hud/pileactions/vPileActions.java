/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * vPileActions.java
 *
 */
public class vPileActions extends Table {

	private static final Texture JAUGE = new Texture(Gdx.files.internal("pileactions/jauge.png"));

	//Position et taille de la pile d'actions
	private static int X = 20;
	private static int Y = 700;
	private static int WIDTH = 100;
	private static int HEIGHT = 200;
	private static int LEFT_WIDTH = 50;

	public vPileActions() {
		setPosition(X, Y);
		setSize(WIDTH, HEIGHT);
		addActor(new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(JAUGE, X, Y, LEFT_WIDTH, HEIGHT);
			}
		});
	}

}
