/*
 * 
 * 
 * 
 */
package vue.hud.timeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.Couleur;
import vue.hud.bulle.BulleListener;
import vue.hud.vHud;

/**
 * vTimelineEntite.java
 *
 */
public class vTimelineEntite extends Actor implements Tourable {

	//Couleur de fond
	private static final Color FOND_COULEUR = Couleur.get("fond_perso", "hud", "timeline");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_perso_contour", "hud", "timeline");

	//Tableau des textures des portraits
	private static final String[] TIMELINE_PORTRAIT = {
		"timeline/perso1.png",
		"timeline/perso2.png"
	};

	//Position et taille d'une entité de timeline
	private static final int TEXTURE_WIDTH = 75;
	private static final int TEXTURE_HEIGHT = TEXTURE_WIDTH;
	private static final int TEXTURE_Y = 10;

	//Ecart X
	private static final int TEXTURE_X_ECART = 50;

	//Position X
	private int TEXTURE_X;

	//Index de la texture
	private final Texture texture;

	//Ordre de la texture
	private int ordre;

	//Scale de la texture (modifie la taille)
	private float scale;

	/**
	 *
	 * @param entite
	 * @param ordreX  représente l'ordre de l'entité dans la timeline, de 0 à N.
	 *                0 représente l'entité jouant son tour.
	 * @param manager
	 */
	public vTimelineEntite(EntiteActive entite, int ordreX, AssetManager manager) {
		ordre = ordreX;
		setY(TEXTURE_Y);
		setScale();
		texture = manager.get(TIMELINE_PORTRAIT[entite.getIndexTextureTimeline()]);
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return entite.getNom() + " lvl " + entite.getNiveauSymbol();
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		vHud.drawBackground(getParent().getX() + getX(), getParent().getY() + getY(), getWidth(), getHeight(), FOND_COULEUR, FOND_CONTOUR_COULEUR);
		batch.begin();
		batch.draw(texture, getX() + 5, getY() + 5, getWidth() - 10, getHeight() - 10);
	}

	/**
	 * Défini le coefficient de taille
	 */
	private void setScale() {
		scale = (ordre == 0) ? 1.20f : 1f;
		TEXTURE_X = ordre * (TEXTURE_WIDTH + TEXTURE_X_ECART) + TEXTURE_X_ECART;
		setX(TEXTURE_X);
		setSize(TEXTURE_WIDTH * scale, TEXTURE_HEIGHT * scale);
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		ordre = (ordre == 0) ? (int) objs[0] : ordre - 1;
		setScale();
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
