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
import vue.Couleur;
import vue.hud.vHud;

/**
 * vTempsAction.java
 *
 */
public class vTempsAction extends Actor {

	private static final Color[] COULEURS = {
		Couleur.get("deplacement", "hud", "pile_action"), //Déplacement
		Couleur.get("sort", "hud", "pile_action"), //Sort
		new Color(Color.ORANGE)
	};

	private final int index;

	private final int tempsActionMax;
	private int tempsActionActu;

	public vTempsAction(int indexCouleur, int tempsAction) {
		index = indexCouleur;
		tempsActionMax = tempsActionActu = tempsAction;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		vHud.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		vHud.shapeRenderer.setColor(COULEURS[index]);
		vHud.shapeRenderer.rect(getParent().getX() + getX() + 1, getParent().getY() + getY(), getWidth() - 2, getHeight());
		vHud.shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

		batch.begin();
	}

	public boolean actu() {
		tempsActionActu -= 10;
		return tempsActionActu > 0;
	}

}
