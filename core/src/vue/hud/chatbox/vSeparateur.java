/*
 * 
 * 
 * 
 */
package vue.hud.chatbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import vue.Couleur;
import static vue.hud.vHud.shapeRenderer;

/**
 * vSeparateur.java
 * 
 */
public class vSeparateur extends Actor {
	
	private static Color FOND_COULEUR = Couleur.get("separateur", "hud", "chat");
	
	private float initY;
	
	public vSeparateur(int width, int height) {
		setSize(width, height);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(FOND_COULEUR);
		shapeRenderer.rect(getParent().getX() + getParent().getParent().getX() + getX(), getParent().getParent().getY() + getParent().getY() + getY(), getWidth(), getHeight());
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		batch.begin();
	}

	public float getInitY() {
		return initY;
	}

	public void setInitY(float initY) {
		this.initY = initY;
	}

}
