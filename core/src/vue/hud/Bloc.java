/*
 * 
 * 
 * 
 */
package vue.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
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
//		debugAll();
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
