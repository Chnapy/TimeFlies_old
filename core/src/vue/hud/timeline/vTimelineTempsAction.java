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
import gameplay.entite.EntiteActive;
import java.util.Observable;
import java.util.Observer;

/**
 * vTimelineTempsAction.java
 *
 */
public class vTimelineTempsAction extends Actor {

	//Texture de la barre
	private static final Texture TEXTURE = new Texture(Gdx.files.internal("timeline/timeline_taction.png"));

	//Position et taille de la texture
	private static final int TEXTURE_X = 50;
	private static final int TEXTURE_Y = 2;
	private static final int TEXTURE_WIDTH = 1720;
	private static final int TEXTURE_HEIGHT = 8;

	//Coefficient de taille
	private float scale;

	static {
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	public vTimelineTempsAction() {
		scale = 1;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(TEXTURE, TEXTURE_X, TEXTURE_Y, TEXTURE_WIDTH * scale, TEXTURE_HEIGHT);
	}

	@Override
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void tourEnCours(EntiteActive entiteEnCours) {
		int tempsActu = entiteEnCours.getTempsAction().getActu();
		if (tempsActu >= 0) {
			int tempsTotal = entiteEnCours.getTempsAction().getTotal();

			scale = (float) tempsActu / tempsTotal;
		}
	}

}
