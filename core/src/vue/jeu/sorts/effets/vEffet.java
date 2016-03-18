/*
 * 
 * 
 * 
 */
package vue.jeu.sorts.effets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import general.GridPointFloat2;

/**
 * vEffet.java
 *
 */
public abstract class vEffet extends Group {

	protected final AssetManager manager;

	protected final int percentStart;
	protected final int percentDuree;
	protected float dureeReelle;
	protected float dureeStart;
	protected final GridPointFloat2 position;
	protected GridPointFloat2 positionStart;
	protected GridPointFloat2 positionEnd;
	protected long tempsStart;
	protected int tempsAction;
	protected int width;
	protected int height;
	protected float delta;

	public vEffet(AssetManager _manager, int _percentStart, int _percentDuree, GridPointFloat2 _position) {
		manager = _manager;
		percentStart = _percentStart;
		percentDuree = _percentDuree;
		position = _position;
	}

	public boolean estVisible() {
		long temps = System.currentTimeMillis() - tempsStart - (long) dureeStart;
		return temps > 0 && temps <= dureeReelle;
	}

	public void lancer(int tempsAction, GridPointFloat2 posStart, GridPointFloat2 posEnd) {
		tempsStart = System.currentTimeMillis();
		this.tempsAction = tempsAction;
		dureeReelle = (float) percentDuree / 100 * tempsAction;
		dureeStart = (float) percentStart / 100 * tempsAction;
		positionStart = new GridPointFloat2(posStart);
		positionEnd = new GridPointFloat2(posEnd);
		delta = 0;
		start();
	}

	@Override
	public void act(float delta) {
		if (estVisible()) {
			super.act(delta);
			update(delta);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (estVisible()) {
			super.draw(batch, parentAlpha);
			delta += Gdx.graphics.getDeltaTime();
			render(batch, parentAlpha);
		}
	}

	protected abstract void start();

	protected abstract void update(float delta);

	protected abstract void render(Batch batch, float parentAlpha);

}
