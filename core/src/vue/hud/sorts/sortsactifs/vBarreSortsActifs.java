/*
 * 
 * 
 * 
 */
package vue.hud.sorts.sortsactifs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import controleur.cCombat;
import gameplay.sort.SortActif;
import static test.MainTest.MAX_WIDTH;
import vue.hud.sorts.vSortsBouton;
import static vue.hud.vHud.FONT;

/**
 * vBarreSortsActifs.java
 *
 */
public class vBarreSortsActifs extends HorizontalGroup {

	private final Texture TEXTURE = new Texture(Gdx.files.internal("sort/barre_sorts_actifs.png"));

	private static final int WIDTH = 800;
	private static final int HEIGHT = 128;
	private static final int X = MAX_WIDTH - WIDTH - 152;
	private static final int Y = 12;

	public vBarreSortsActifs() {
		TEXTURE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		setSize(WIDTH, HEIGHT);
		setPosition(X, Y);
		this.addActor(new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(TEXTURE, 0, 0, WIDTH, HEIGHT);
			}
		});
		vSortsActifsBouton.filterTexture();
//		debugAll();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		FONT.setColor(1, 1, 1, 0.75f);
		FONT.drawMultiLine(batch, "SORTS ACTIFS", getX() + 14, getY() + getHeight() - 10);
		FONT.setColor(1, 1, 1, 1);
	}

	public void addBouton(vSortsBouton bouton) {
		this.addActor(bouton);
		this.align(Align.left);
		this.space(16);
		this.padTop(16);
	}

	public void nouveauTour(cCombat ccombat, SortActif[] sactifs) {
		for (SortActif sort : sactifs) {
			addBouton(new vSortsActifsBouton(ccombat, sort.getIndex(), 5, 10, 8, 2));
		}
	}

	public void finTour() {
		getChildren().removeRange(1, getChildren().size - 1);
	}

}
