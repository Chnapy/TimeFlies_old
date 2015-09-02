/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import vue.Couleur;
import vue.hud.vHud;

/**
 * vSortsBouton.java
 *
 */
public abstract class vSortsBouton extends Actor {

	//Taille de la texture
	private static final int TEXTURE_WIDTH = 64;
	private static final int TEXTURE_HEIGHT = 64;

	//Couleur de fond
	private static final Color FOND_COULEUR = Couleur.get("fond_sort", "hud", "sort", "actif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_sort_contour", "hud", "sort", "actif");

	//Texture du sort
	private final Texture TEXTURE;

	public vSortsBouton(Texture texture) {
		TEXTURE = texture;
		setSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		vHud.drawBackground(getParent().getX() + getX(), getParent().getY() + getY(), getWidth(), getHeight(), FOND_COULEUR, FOND_CONTOUR_COULEUR);
		batch.begin();
		batch.draw(TEXTURE, getX(), getY(), getWidth(), getHeight());
		drawIcon(batch, parentAlpha);
	}

	//Affichage des icones
	protected abstract void drawIcon(Batch batch, float parentAlpha);

}
