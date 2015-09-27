/*
 * 
 * 
 * 
 */
package vue.hud.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import gameplay.sort.Sort;
import general.TypeDonnee;
import static test.MainTest.camera;
import vue.Couleur;
import vue.hud.tabcarac.Donnee;
import static vue.hud.vHud.shapeRenderer;

/**
 * vSortsBouton.java
 *
 */
public abstract class vSortsBouton extends Group {

	//Taille de la texture
	private static final int TEXTURE_WIDTH = 70;
	private static final int TEXTURE_HEIGHT = 70;

	//Couleur de fond
	private static final Color FOND_COULEUR = Couleur.get("fond_sort", "hud", "sort", "actif");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_sort_contour", "hud", "sort", "actif");

	//Texture du sort
	private final Texture TEXTURE;
	protected final Sort sort;
	protected final Pool<Donnee> poolDonnees;
	protected final Array<Donnee> donnees;

	public vSortsBouton(Sort _sort, Texture texture) {
		TEXTURE = texture;
		sort = _sort;
		setSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
		Image tt = new Image(TEXTURE);
		tt.setSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
		addActor(tt);
		donnees = new Array<Donnee>();
		poolDonnees = new Pool<Donnee>() {
			@Override
			protected Donnee newObject() {
				return new Donnee();
			}
		};
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//Fond
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(FOND_COULEUR);
		shapeRenderer.circle(getParent().getX() + getX() + getWidth() / 2, getParent().getY() + getY() + getHeight() / 2, getWidth() / 2, 100);
		shapeRenderer.end();
		//Contour
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		Gdx.gl20.glLineWidth(4 / camera.zoom);
		shapeRenderer.setColor(FOND_CONTOUR_COULEUR);
		shapeRenderer.circle(getParent().getX() + getX() + getWidth() / 2, getParent().getY() + getY() + getHeight() / 2, getWidth() / 2 + 1, 100);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		Gdx.gl20.glLineWidth(1 / camera.zoom);
		batch.begin();
		super.draw(batch, parentAlpha);
	}

	public Sort getSort() {
		return sort;
	}

}
