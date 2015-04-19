/*
 * 
 * 
 * 
 */
package vue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import controleur.cCombat;

/**
 * vTuile.java
 *
 */
public class vTuile extends Actor {

	public static final int TUILE_WIDTH = 128;
	public static final int TUILE_HEIGHT = 64;
	public static final int OFFSET_X = 500;
	public static final int OFFSET_Y = 300;

	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("tuile/tuile.atlas"));
	private static final Sprite[] tabSprite = {
		new Sprite(atlas.findRegion("tuileSimple")),
		new Sprite(atlas.findRegion("tuileTrou")),
		new Sprite(atlas.findRegion("tuileObstacle")),
		new Sprite(atlas.findRegion("tuileEcran"))
	};

	private int posX;
	private int posY;
	private int x;
	private int y;
	private int iSprite;
	private Color couleur = Color.WHITE;

	public vTuile(int posx, int posy, int indexSprite, cCombat ccombat) {
		posX = posx;
		posY = posy;
		x = OFFSET_X + posx * TUILE_WIDTH / 2 + posy * -TUILE_HEIGHT;
		y = OFFSET_Y + posx * -TUILE_WIDTH / 4 + posy * -TUILE_HEIGHT / 2;
		iSprite = indexSprite;

		setBounds(x, y, TUILE_WIDTH, TUILE_HEIGHT);
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down : " + x + " " + y);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float X, float Y,
					int pointer, int button) {
				System.out.println("up : " + X + " " + Y);
				ccombat.clicSurTuile(posx, posy);	//Envoi de la tuile (gameplay) au controleur
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				couleur = Color.WHITE;
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				couleur = Color.RED;
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(couleur);
		batch.draw(tabSprite[iSprite], x, y);
	}

}
