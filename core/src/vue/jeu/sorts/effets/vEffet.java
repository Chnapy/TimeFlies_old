/*
 * 
 * 
 * 
 */
package vue.jeu.sorts.effets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.awt.Point;

/**
 * vEffet.java
 *
 */
public abstract class vEffet extends Actor {
	
	protected final AssetManager manager;

	protected final int start;
	protected final int duree;
	protected float dureeReelle;
	protected float dureeStart;
	protected final Point position;
	protected Point positionStart;
	protected Point positionEnd;
	protected long tempsStart;
	protected int tempsAction;
	protected int width;
	protected int height;
	protected float delta;

	public vEffet(AssetManager _manager, int _start, int _duree, Point _position) {
		manager = _manager;
		start = _start;
		duree = _duree;
		position = _position;
	}

	public boolean estVisible() {
		long temps = System.currentTimeMillis() - tempsStart - (long) dureeStart;
		return temps > 0 && temps <= dureeReelle;
	}

	public void lancer(int tempsAction, float[] posStart, float[] posEnd) {
		tempsStart = System.currentTimeMillis();
		this.tempsAction = tempsAction;
		dureeReelle = (float) duree / 100 * tempsAction;
		dureeStart = (float) start / 100 * tempsAction;
		positionStart = new Point((int) posStart[0], (int) posStart[1]);
		positionEnd = new Point((int) posEnd[0], (int) posEnd[1]);
		delta = 0;
		start();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (estVisible()) {
			delta += Gdx.graphics.getDeltaTime();
			render(batch, parentAlpha);
		}
	}

	protected abstract void start();

	protected abstract void render(Batch batch, float parentAlpha);

}
