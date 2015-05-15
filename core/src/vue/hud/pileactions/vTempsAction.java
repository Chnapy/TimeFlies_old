/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * vTempsAction.java
 *
 */
public class vTempsAction extends Actor {

	private static final Color[] COULEURS = {
		new Color(Color.GREEN), //Déplacement
		new Color(Color.RED),
		new Color(Color.ORANGE)
	};

	private final ShapeRenderer shapeRenderer;
	private final int index;

	private int tempsActionMax;
	private int tempsActionActu;

	public vTempsAction(ShapeRenderer shapeRender, int indexTexture, int tempsAction) {
		shapeRenderer = shapeRender;
		index = indexTexture;
		tempsActionMax = tempsActionActu = tempsAction;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(COULEURS[index]);
		shapeRenderer.getColor().a = 0.25f;
		shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
		shapeRenderer.getColor().a = 1;
		shapeRenderer.rect(getX(), getY(), getWidth(), getHeight() * tempsActionActu / tempsActionMax);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

		batch.begin();
	}

	public boolean actu() {
		tempsActionActu -= 10;
		return tempsActionActu > 0;
	}

}
