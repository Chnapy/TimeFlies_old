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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import controleur.cCombat;
import vue.hud.sorts.vSortsBouton;
import static vue.hud.vHud.FONT;

/**
 * vSortsActifsBouton.java
 *
 */
public class vSortsActifsBouton extends vSortsBouton {

	//Tableau de textures des sorts
	private static final Texture[] TEXTURES = {
		new Texture(Gdx.files.internal("sort/sort_fond.png")),
		new Texture(Gdx.files.internal("sort/sort_fond.png"))
	};

	//Textures des icones
	private static final Texture ICONE_TEMPS = new Texture(Gdx.files.internal("sort/icon/icon_temps.png"));
	private static final Texture ICONE_PORTEE = new Texture(Gdx.files.internal("sort/icon/icon_portee.png"));
	private static final Texture ICONE_ZONE = new Texture(Gdx.files.internal("sort/icon/icon_zone.png"));
	private static final Texture ICONE_RELANCE = new Texture(Gdx.files.internal("sort/icon/icon_relance.png"));

	//Taille des icones
	private static final int ICONE_WIDTH = 16;
	private static final int ICONE_HEIGHT = 16;

	static {
		ICONE_TEMPS.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		ICONE_PORTEE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		ICONE_ZONE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		ICONE_RELANCE.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	//Valeur des différentes données du sort
	private String temps;
	private String portee;
	private String zone;
	private String relance;

	public vSortsActifsBouton(cCombat ccombat, int index, int temps, int portee, int zone, int relance) {
		super(TEXTURES[index]);
		this.temps = Integer.toString(temps);
		this.portee = Integer.toString(portee);
		this.zone = Integer.toString(zone);
		this.relance = Integer.toString(relance);
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float X, float Y,
					int pointer, int button) {
				//Envoyer au controleur
				ccombat.modeSort(index);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

			}
		});
	}

	public static final void filterTexture() {
		for (Texture texture : TEXTURES) {
			texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}

	@Override
	protected void drawIcon(Batch batch, float parentAlpha) {
		batch.setColor(1, 1, 1, 0.5f);
		batch.draw(ICONE_TEMPS, getX() + 8, getY() + getHeight() - ICONE_HEIGHT - 8, ICONE_WIDTH, ICONE_HEIGHT);
		batch.draw(ICONE_RELANCE, getX() + 8, getY() + ICONE_HEIGHT - 8, ICONE_WIDTH, ICONE_HEIGHT);
		batch.draw(ICONE_PORTEE, getX() + getWidth() - ICONE_WIDTH - 8, getY() + getHeight() - ICONE_HEIGHT - 8, ICONE_WIDTH, ICONE_HEIGHT);
		batch.draw(ICONE_ZONE, getX() + getWidth() - ICONE_WIDTH - 8, getY() + ICONE_HEIGHT - 8, ICONE_WIDTH, ICONE_HEIGHT);
		batch.setColor(1, 1, 1, 1);

		FONT.setColor(0, 0, 0, 1);
		FONT.draw(batch, temps, getX() + 8, getY() + getHeight() - 8);
		FONT.draw(batch, portee, getX() + 8, getY() + ICONE_HEIGHT + 8);
		FONT.draw(batch, zone, getX() + getWidth() - ICONE_WIDTH - 8, getY() + getHeight() - ICONE_HEIGHT + 8);
		FONT.draw(batch, relance, getX() + getWidth() - ICONE_WIDTH - 8, getY() + ICONE_HEIGHT + 8);
	}

}
