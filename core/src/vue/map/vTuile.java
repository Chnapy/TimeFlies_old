/*
 * 
 * 
 * 
 */
package vue.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import controleur.cCombat;

/**
 * vTuile.java
 * Vue de chaque tuile.
 * Gère également les actions user.
 *
 */
public class vTuile extends Actor {

	public static final int TUILE_WIDTH = 128;
	public static final int TUILE_HEIGHT = 64;
	public static final int OFFSET_X = 0;
	public static final int OFFSET_Y = 250;

	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("tuile/tuile.atlas"));
	private static final Sprite[] tabSprite = {
		new Sprite(atlas.findRegion("tuileSimple")),
		new Sprite(atlas.findRegion("tuileTrou")),
		new Sprite(atlas.findRegion("tuileObstacle")),
		new Sprite(atlas.findRegion("tuileEcran"))
	};

	private int posX;
	private int posY;
	private float x;
	private float y;
	private int iSprite;
	private Color couleur = Color.WHITE;
	BitmapFont lab;

	public vTuile(int posx, int posy, int indexSprite, cCombat ccombat) {
		this.lab = new BitmapFont();
		posX = posx;
		posY = posy;
		float[] pos = getPosition(posx, posy);
		x = pos[0];
		y = pos[1];
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
				ccombat.clicSurTuile(posx, posy);	//Déplacement/Lancement de sort
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				couleur = Color.WHITE;
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				couleur = Color.RED;
				ccombat.survolTuile(posx, posy);	//Affichage déplacement possible
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(couleur);
		batch.draw(tabSprite[iSprite], x, y);
		lab.setColor(Color.BLACK);
		lab.draw(batch, posX + "_" + posY, x + 250, y + 150);
	}

	/**
	 * Récupération de la position réelle.
	 * 
	 * @param x
	 * @param y
	 * @return 
	 */
	public static float[] getPosition(int x, int y) {
		return new float[]{
			OFFSET_X + x * TUILE_WIDTH / 2 + y * TUILE_WIDTH / 2,
			OFFSET_Y + x * TUILE_HEIGHT / 2 + y * -TUILE_HEIGHT / 2
		};
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	/**
	 * Lancé lors de l'application du pathfinding dans la vue.
	 * 
	 * @param dansChemin 
	 */
	public void tuileDuChemin(boolean dansChemin) {
		if (couleur != Color.RED) {
			if (dansChemin) {
				couleur = Color.ORANGE;
			} else {
				couleur = Color.WHITE;	//Revient à enlever toute couleur
			}
		}
	}

}
