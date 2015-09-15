/*
 * 
 * 
 * 
 */
package vue.jeu.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import controleur.cCombat;
import gameplay.map.EtatTuile;
import static gameplay.map.EtatTuile.NORMAL;
import static test.MainTest.camera;
import vue.Couleur;
import vue.hud.bulle.BulleListener;
import static vue.hud.vHud.shapeRenderer;
import static vue.jeu.map.vTuile.couleurs.*;

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

	public enum couleurs {

		SIMPLE(Couleur.get("simple", "jeu", "map", "fond"), Couleur.get("simple", "jeu", "map", "contour"), 0),
		TROU(Couleur.get("trou", "jeu", "map", "fond"), Couleur.get("trou", "jeu", "map", "contour"), 1),
		ECRAN(Couleur.get("ecran", "jeu", "map", "fond"), Couleur.get("ecran", "jeu", "map", "contour"), 2),
		OBSTACLE(Couleur.get("obstacle", "jeu", "map", "fond"), Couleur.get("obstacle", "jeu", "map", "contour"), 3),
		CIBLE(Couleur.get("cible", "jeu", "map", "fond"), Couleur.get("cible", "jeu", "map", "contour"), 4),
		ZONEPORTEE(Couleur.get("zoneportee", "jeu", "map", "fond"), Couleur.get("zoneportee", "jeu", "map", "contour"), 5),
		ZONEACTION(Couleur.get("zoneaction", "jeu", "map", "fond"), Couleur.get("zoneaction", "jeu", "map", "contour"), 6),
		CHEMIN(Couleur.get("chemin", "jeu", "map", "fond"), Couleur.get("chemin", "jeu", "map", "contour"), 7),
		DESTINATION(Couleur.get("destination", "jeu", "map", "fond"), Couleur.get("destination", "jeu", "map", "contour"), 8),
		GHOSTZONEACTION(null, Couleur.get("ghostzoneaction", "jeu", "map", "contour"), 9),
		GHOSTCHEMIN(null, Couleur.get("ghostchemin", "jeu", "map", "contour"), 10),
		GHOSTDESTINATION(null, Couleur.get("ghostdestination", "jeu", "map", "contour"), 11);

		public final Color fond;
		public final Color contour;
		private final int index;

		couleurs(Color _fond, Color _contour, int _index) {
			fond = _fond;
			contour = _contour;
			index = _index;
		}

		static couleurs getColor(int index) {
			for (couleurs c : values()) {
				if (c.index == index) {
					return c;
				}
			}
			throw new IllegalArgumentException();
		}
	};

	private final PolygonSprite polySprite;
	private final PolygonSpriteBatch polyContour;
	private final Polygon poly;

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

	//Couleur du type de la tuile
	private couleurs TYPE;

	//Couleur de la tuile (WHITE = transparent)
	private couleurs theme;

	private couleurs contour;

	private Array<couleurs> pile_deplacement;

	public vTuile(int posx, int posy, int indexType, EtatTuile e, cCombat ccombat) {
		posX = posx;
		posY = posy;
		float[] pos = getPosition(posx, posy);
		x = pos[0];
		y = pos[1];
		TYPE = couleurs.getColor(indexType);
		etat = e;

		poly = new Polygon(new float[]{
			TUILE_WIDTH / 2, 0 + TUILE_MARGIN / 2,
			TUILE_WIDTH - TUILE_MARGIN, TUILE_HEIGHT / 2,
			TUILE_WIDTH / 2, TUILE_HEIGHT - TUILE_MARGIN / 2,
			0 + TUILE_MARGIN, TUILE_HEIGHT / 2
		});
		poly.setPosition(x, y);

		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(1, 1, 1, 1);
		pix.fill();
		Texture textureSolid = new Texture(pix);
		PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
				poly.getVertices(),
				new short[]{
					0, 1, 2,
					0, 3, 2
				});

		polySprite = new PolygonSprite(polyReg);
		polySprite.setPosition(x, y);

		polyContour = new PolygonSpriteBatch();
		polyContour.setProjectionMatrix(camera.combined);

		contour = null;

		pile_deplacement = new Array<couleurs>();

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
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "Cette tuile est de type n" + TYPE;
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		polyContour.begin();
		polySprite.setColor(theme.fond);
		polySprite.draw(polyContour);
		polyContour.end();
		Gdx.gl20.glLineWidth(3 / camera.zoom);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		if (contour == null) {
			shapeRenderer.setColor(theme.contour);
		} else {
			shapeRenderer.setColor(contour.contour);
		}
		shapeRenderer.polygon(poly.getTransformedVertices());
		shapeRenderer.end();
		Gdx.gl20.glLineWidth(1 / camera.zoom);

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
	 * Défini la fond de la tuile selon son etat, hover et action
	 */
	private void setCouleur() {
		if (action) {
			theme = ZONEACTION;
		} else if (!hover) {
			switch (etat) {
				case NORMAL:
					theme = TYPE;
					break;
				case PATH:
					theme = CHEMIN;
					break;
				case ZONESORT:
					theme = ZONEPORTEE;
					break;
				default:
					throw new Error("Etat tuile non géré");
			}
		} else {
			switch (etat) {
				case NORMAL:
					theme = DESTINATION;
					break;
				case PATH:
					break;
				case ZONESORT:
					theme = CIBLE;
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

	public void addGhostPath() {
		pile_deplacement.add(hover ? GHOSTDESTINATION : GHOSTCHEMIN);
		if (contour != GHOSTDESTINATION) {
			contour = hover ? GHOSTDESTINATION : GHOSTCHEMIN;
		}
	}

	public void addGhostAction() {
		contour = GHOSTZONEACTION;
	}

	public void clearGhostPath() {
		clearGhostPath(false);
	}

	public void clearGhostPath(boolean forcing) {
		if (forcing) {
			pile_deplacement.clear();
			contour = null;
		} else {
			if (pile_deplacement.size > 0) {
				pile_deplacement.removeIndex(0);
			}
			if (pile_deplacement.size > 0) {
				contour = pile_deplacement.first();
			} else {
				contour = null;
			}
		}
	}

	public void clearGhostZoneAction() {
		if (contour == GHOSTZONEACTION) {
			contour = null;
		}
	}

}
