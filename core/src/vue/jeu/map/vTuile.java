/*
 * 
 * 
 * 
 */
package vue.jeu.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import controleur.cCombat;
import gameplay.map.EtatTuile;
import static gameplay.map.EtatTuile.NORMAL;

/**
 * vTuile.java
 * Vue de chaque tuile.
 * Gère également les actions user.
 *
 */
public class vTuile extends Actor {

	//Taille du sprite de la tuile
	public static final int TUILE_WIDTH = 256;
	public static final int TUILE_HEIGHT = 128;

	private static final int TUILE_MARGIN = 24;

	//Ecart entre les bords de la fenetre et les tuiles
	public static final int OFFSET_X = 0;
	public static final int OFFSET_Y = 500;

	private static final Color[] tabInitColor = {
		new Color(1, 1, 1, 0.5f),
		new Color(0, 0, 0, 0.5f),
		new Color(1, 0, 0, 0.5f),
		new Color(0, 1, 0, 0.5f)
	};

	private static final Color[] tabColor = {
		Color.RED, //Action
		Color.YELLOW, //Path
		Color.CYAN, //Zone sort
		Color.ORANGE, //Hover normal
		Color.BLUE, //Hover zone sort
	};

	static {
		for (Color color : tabColor) {
			color.a = 0.75f;
		}
	}

	private final PolygonSprite polySprite;
	private final Texture textureSolid;

	//Etat de la tuile (sort, déplacement, ...)
	private EtatTuile etat;

	//Survol de la tuile par la souris
	private boolean hover;

	//Tuile affectée par la zone d'action
	private boolean action;

	//Position relative
	private int posX;
	private int posY;

	//Position absolue
	private float x;
	private float y;

	//Index du sprite
	private int iSprite;

	//Couleur de la tuile (WHITE = transparent)
	private Color couleur;

	//Debug position
	private final BitmapFont lab;

	public vTuile(int posx, int posy, int indexSprite, EtatTuile e, cCombat ccombat) {
		this.lab = new BitmapFont();
		lab.setColor(Color.BLACK);
		posX = posx;
		posY = posy;
		float[] pos = getPosition(posx, posy);
		x = pos[0];
		y = pos[1];
		iSprite = indexSprite;
		etat = e;

		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(1, 1, 1, 0.75f);
		pix.fill();
		textureSolid = new Texture(pix);
		PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
				new float[]{
					TUILE_WIDTH / 2, 0 + TUILE_MARGIN / 2,
					TUILE_WIDTH - TUILE_MARGIN, TUILE_HEIGHT / 2,
					TUILE_WIDTH / 2, TUILE_HEIGHT - TUILE_MARGIN / 2,
					0 + TUILE_MARGIN, TUILE_HEIGHT / 2
				}, new short[]{
					0, 1, 2,
					0, 3, 2
				});

		polySprite = new PolygonSprite(polyReg);
		polySprite.setPosition(x, y);

		setBounds(x, y, TUILE_WIDTH, TUILE_HEIGHT);
		setPosition(x, y);
		setSize(TUILE_WIDTH, TUILE_HEIGHT);
		addListener(new InputListener() {
			//Pression souris
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
//				System.out.println("down : " + x + " " + y);
				return true;
			}

			//Relachement souris
			@Override
			public void touchUp(InputEvent event, float X, float Y,
					int pointer, int button) {
//				System.out.println("up : " + X + " " + Y);
				ccombat.clicSurTuile(posx, posy);	//Déplacement/Lancement de sort
			}

			//Fin survol souris
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				setHover(false);
			}

			//Debut survol souris
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				setHover(true);
				ccombat.survolTuile(posx, posy);	//Affichage déplacement possible
//				System.out.println(etat);
			}
		});
		setCouleur();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		polySprite.setColor(couleur);
		polySprite.draw((PolygonSpriteBatch) batch);
//		lab.draw(batch, "x" + posX + "_y" + posY, getX() + getWidth() / 2, getY() + getHeight() / 2);

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

	public EtatTuile getEtat() {
		return etat;
	}

	public void setEtat(EtatTuile newEtat) {
		etat = newEtat;
		setCouleur();
	}

	private void setHover(boolean h) {
		hover = h;
		setCouleur();
	}

	/**
	 * Défini la couleur de la tuile selon son etat, hover et action
	 */
	private void setCouleur() {
		if (action) {
			couleur = tabColor[0];
		} else if (!hover) {
			switch (etat) {
				case NORMAL:
					couleur = tabInitColor[iSprite];
					break;
				case PATH:
					couleur = tabColor[1];
					break;
				case ZONESORT:
					couleur = tabColor[2];
					break;
				default:
					throw new Error("Etat tuile non géré");
			}
		} else {
			switch (etat) {
				case NORMAL:
					couleur = tabColor[3];
					break;
				case PATH:
					break;
				case ZONESORT:
					couleur = tabColor[4];
					break;
				default:
					throw new Error("Etat tuile non géré");
			}
		}
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
		setCouleur();
	}

	public int getPosX() {
		return posX;
	}

	/**
	 * Redéfini sa hitbox pour le survol de la souris
	 *
	 * @param x
	 * @param y
	 * @param touchable
	 * @return
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (super.hit(x, y, touchable) != null) {
			float Px = getWidth() / 2, Py = getHeight() / 2;
			float Vx = Math.abs(x - Px), Vy = Math.abs(y - Py);
			if (Py * ((Px - Vx) / Px) >= Vy) {
				return this;
			}
			return null;
		}
		return null;
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
		if (!hover) {
			setEtat(dansChemin ? EtatTuile.PATH : EtatTuile.NORMAL);
		}
	}

}
