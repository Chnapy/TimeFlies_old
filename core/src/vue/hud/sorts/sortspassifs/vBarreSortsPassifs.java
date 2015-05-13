/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortspassifs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import controleur.cCombat;
import gameplay.sort.SortPassif;
import static test.MainTest.MAX_WIDTH;
import vue.hud.sorts.vSortsBouton;
import static vue.hud.vHud.FONT;

/**
 * vBarreSortsPassifs.java
 *
 */
public class vBarreSortsPassifs extends VerticalGroup {

	//Texture de fond de la barre
	private final Texture TEXTURE = new Texture(Gdx.files.internal("sort/barre_sorts_passifs.png"));

	//Position et taille de la barre
	private static final int WIDTH = 128;
	private static final int HEIGHT = 800;
	private static final int X = MAX_WIDTH - WIDTH - 12;
	private static final int Y = 152;

	public vBarreSortsPassifs() {
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		this.addActor(new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(TEXTURE, 0, 0, WIDTH, HEIGHT);
			}
		});
//		debugAll();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		FONT.setColor(1, 1, 1, 0.75f);
		FONT.drawMultiLine(batch, "SORTS\nPASSIFS", getX() + 14, getY() + getHeight() - 10);
		FONT.setColor(1, 1, 1, 1);
	}

	//Ajout d'un bouton de sort
	public void addBouton(vSortsBouton bouton) {
		this.addActor(bouton);
		this.align(Align.top);
		this.space(16);
		this.padTop(50);
	}

	/**
	 * Nouveau tour d'une entité
	 *
	 * @param ccombat
	 * @param spassifs
	 */
	public void nouveauTour(cCombat ccombat, SortPassif[] spassifs) {
		for (SortPassif sort : spassifs) {
			addBouton(new vSortsPassifsBouton(sort.getIndex()));
		}
	}

	/**
	 * Fin tour d'une entité
	 *
	 */
	public void finTour() {
		if (getChildren().size > 1) {
			getChildren().removeRange(1, getChildren().size - 1);
		}
	}

}
