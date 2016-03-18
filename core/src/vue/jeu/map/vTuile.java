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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.map.EtatTuile;
import static gameplay.map.EtatTuile.NORMAL;
import static gameplay.map.EtatTuile.NULL;
import static gameplay.map.EtatTuile.PATH;
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

	private static final float COEFF_TUILE = 0.08f;
	private static final float COEFF_MINITUILE = 0.10f;

	//Ecart entre les bords de la fenetre et les tuiles
	public static final int OFFSET_X = 0;
	public static final int OFFSET_Y = 500;

	public enum couleurs {

		SIMPLE(Couleur.get("simple", "jeu", "map", "fond"), Couleur.get("simple", "jeu", "map", "contour"), 0),
		TROU(Couleur.get("trou", "jeu", "map", "fond"), Couleur.get("trou", "jeu", "map", "contour"), 1),
		ECRAN(Couleur.get("ecran", "jeu", "map", "fond"), Couleur.get("ecran", "jeu", "map", "contour"), 3),
		OBSTACLE(Couleur.get("obstacle", "jeu", "map", "fond"), Couleur.get("obstacle", "jeu", "map", "contour"), 2),
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
	private final PolygonSprite minipolySprite;
	private final PolygonSpriteBatch polyContour;
	private final Polygon poly;
	private final Polygon minipoly;

	//Etat grande tuile
	private EtatTuile etat;

	//Etat petite tuile
	private EtatTuile minietat;

	//Survol de la tuile par la souris
	private boolean hover;

	//Tuile affectée par la zone d'action
	private boolean action;

	//Position relative
	private int posX;
	private int posY;

	//Couleur du type de la tuile
	private couleurs TYPE;

	//Couleur de la grande tuile (contour uniquement)
	private couleurs tuileColor;

	//Couleur de la petite tuile
	private couleurs miniTuileColor;

	private Array<EtatTuile> pile_deplacement;

	public vTuile(int posx, int posy, int indexType, EtatTuile e, ControleurPrincipal ccombat, float width, float height) {
		posX = posx;
		posY = posy;
		setSize(width, height);
		TYPE = couleurs.getColor(indexType);
		etat = NORMAL;
		minietat = NULL;
		
		int tuile_margin = (int)(getWidth() * COEFF_TUILE);
		GridPoint2 minituile_margin = new GridPoint2((int)(getWidth() * COEFF_MINITUILE), (int)(getHeight() * COEFF_MINITUILE));

		poly = new Polygon(new float[]{
			width / 2, 0 + tuile_margin / 2,
			width - tuile_margin, height / 2,
			width / 2, height - tuile_margin / 2,
			0 + tuile_margin, height / 2
		});

		minipoly = new Polygon(new float[]{
			width / 2, tuile_margin / 2 + minituile_margin.y,
			width - tuile_margin - minituile_margin.x, height / 2,
			width / 2, height - tuile_margin / 2 - minituile_margin.y,
			tuile_margin + minituile_margin.x, height / 2
		});

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

		PolygonRegion minipolyReg = new PolygonRegion(new TextureRegion(textureSolid),
				minipoly.getVertices(),
				new short[]{
					0, 1, 2,
					0, 3, 2
				});

		minipolySprite = new PolygonSprite(minipolyReg);

		polyContour = new PolygonSpriteBatch();
		polyContour.setProjectionMatrix(camera.combined);

		tuileColor = TYPE;
		miniTuileColor = null;

		pile_deplacement = new Array<EtatTuile>();
		
		addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			//Relachement souris

			@Override
			public void touchUp(InputEvent event, float X, float Y, int pointer, int button) {
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
			}
		});
		setCouleur();
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "Tuile " + TYPE + " Pos " + posX + "." + posY;
			}
		});
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		
		poly.setPosition(getParent().getX() + x, getParent().getY() + y);
		minipoly.setPosition(getParent().getX() + x, getParent().getY() + y);
		polySprite.setPosition(getParent().getX() + x, getParent().getY() + y);
		minipolySprite.setPosition(getParent().getX() + x, getParent().getY() + y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		polyContour.begin();
		polySprite.setColor(TYPE.fond);
		polySprite.draw(polyContour);
		if (miniTuileColor != null) {
			minipolySprite.setColor(miniTuileColor.fond);
			minipolySprite.draw(polyContour);
		}
		polyContour.end();

		Gdx.gl20.glLineWidth(3 / camera.zoom);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		shapeRenderer.setColor(tuileColor.contour);
		shapeRenderer.polygon(poly.getTransformedVertices());

		if (miniTuileColor != null) {
			Gdx.gl20.glLineWidth(2 / camera.zoom);
			shapeRenderer.setColor(miniTuileColor.contour);
			shapeRenderer.polygon(minipoly.getTransformedVertices());
		}

		shapeRenderer.end();
		Gdx.gl20.glLineWidth(1 / camera.zoom);

	}

	public EtatTuile getMinietat() {
		return minietat;
	}

	public void setEtat(EtatTuile newEtat) {
		if (newEtat == NULL || newEtat == PATH || newEtat == EtatTuile.ZONEPORTEE) {
			minietat = newEtat;
		} else {
			etat = newEtat;
		}
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
		switch (etat) {
			case NORMAL:
				tuileColor = TYPE;
				break;
			case GHOSTZONEACTION:
				tuileColor = GHOSTZONEACTION;
				break;
			case GHOSTPATH:
				tuileColor = GHOSTCHEMIN;
				break;
			case GHOSTDESTINATION:
				tuileColor = GHOSTDESTINATION;
				break;
		}
		if (action) {
			miniTuileColor = ZONEACTION;
		} else if (!hover) {
			switch (minietat) {
				case NULL:
					miniTuileColor = null;
					break;
				case PATH:
					miniTuileColor = CHEMIN;
					break;
				case ZONEPORTEE:
					miniTuileColor = ZONEPORTEE;
					break;
			}
		} else {
			switch (minietat) {
				case NULL:
					miniTuileColor = null;
					break;
				case PATH:
					miniTuileColor = DESTINATION;
					break;
				case ZONEPORTEE:
					miniTuileColor = CIBLE;
					break;
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

	public int getPosY() {
		return posY;
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

	/**
	 * Lancé lors de l'application du pathfinding dans la vue.
	 */
	public void addPath() {
		setEtat(EtatTuile.PATH);
	}

	public void clearPath() {
		setEtat(EtatTuile.NULL);
	}

	public void addGhostPath() {
		pile_deplacement.add(hover ? EtatTuile.GHOSTDESTINATION : EtatTuile.GHOSTPATH);
		setEtat(pile_deplacement.peek());
	}

	public void addGhostAction() {
		setEtat(EtatTuile.GHOSTZONEACTION);
	}

	public void clearGhostPath() {
		clearGhostPath(false);
	}

	public void clearGhostPath(boolean forcing) {
		if (forcing) {
			pile_deplacement.clear();
			setEtat(EtatTuile.NORMAL);
		} else {
			if (pile_deplacement.size > 0) {
				pile_deplacement.removeIndex(0);
			}
			if (pile_deplacement.size > 0) {
				setEtat(pile_deplacement.first());
			} else {
				setEtat(EtatTuile.NORMAL);
			}
		}
	}

	public void clearGhostZoneAction() {
		if (etat == EtatTuile.GHOSTZONEACTION) {
			setEtat(EtatTuile.NORMAL);
		}
	}

}
