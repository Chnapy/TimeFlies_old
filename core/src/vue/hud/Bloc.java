/*
 * 
 * 
 * 
 */
package vue.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Bloc.java
 *
 */
public abstract class Bloc extends Window {

	public Bloc(String titre, float width, float height) {
		super(titre, vHud.defaultSkin);
		setSize(width, height + getPadTop());
		setMovable(true);
		getColor().a = 0.75f;
		addListener(new InputListener() {
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				getColor().a = 0.75f;
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				getColor().a = 1;
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		render(batch, parentAlpha);

		batch.begin();
	}

	protected abstract void render(Batch batch, float parentAlpha);

}
