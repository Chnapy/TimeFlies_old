/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import controleur.ControleurPrincipal;
import gameplay.caracteristique.Carac;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import general.Tourable;
import static test.MainTest.camera;
import vue.Couleur;
import vue.Vue;
import static vue.hud.vHud.defaultSkin;
import static vue.hud.vHud.shapeRenderer;

/**
 * EntiteHud.java
 *
 */
public class EntiteHud extends Table implements Tourable {

	private static final Color JAUGE_FOND = Couleur.get("fond", "jeu", "entite", "minihud");
	private static final Color VIE_FOND = Couleur.get("viejauge", "jeu", "entite", "minihud");
	private static final Color VIE_CONTOUR = Couleur.get("viecontour", "jeu", "entite", "minihud");
	private static final Color TEMPS_FOND = Couleur.get("tempsjauge", "jeu", "entite", "minihud");
	private static final Color TEMPS_CONTOUR = Couleur.get("tempscontour", "jeu", "entite", "minihud");

	private final Entite entite;
	private final Label nom;
	private final Jauge vie;
	private final Jauge tempsAction;

	public EntiteHud(Entite _entite, int width, int height) {
		super(defaultSkin);
		setSize(width, height);
		setY(25);
		entite = _entite;

		LabelStyle labStyle = new LabelStyle(defaultSkin.get(LabelStyle.class));
		labStyle.background = defaultSkin.getDrawable("default-scroll");

		if (entite instanceof Personnage) {
			this.nom = new Label(((Personnage) entite).getNomDonne(), labStyle);
		} else {
			this.nom = new Label(entite.getNom(), labStyle);
		}
		this.vie = new Jauge(JAUGE_FOND, VIE_FOND, VIE_CONTOUR);
		this.tempsAction = new Jauge(JAUGE_FOND, TEMPS_FOND, TEMPS_CONTOUR);

		nom.setAlignment(Align.center);
		add(vie).left().width(10).fillY();
		add(nom).expand().top().fillX();
		add(tempsAction).right().width(10).fillY();
		validate();
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		vie.rectangle.height = vie.getHeight() * ((float) entite.getCaracPhysique().get(Carac.VITALITE).getActu()
				/ entite.getCaracPhysique().get(Carac.VITALITE).getTotal());
		tempsAction.rectangle.height = tempsAction.getHeight() * ((float) entite.getCaracPhysique().get(Carac.TEMPSACTION).getActu() / entite.getCaracPhysique().get(Carac.TEMPSACTION).getTotal());
	}

	private class Jauge extends Actor {

		private final Color fond_couleur;
		private final Color jauge_couleur;
		private final Color jauge_contour_couleur;
		public final Rectangle rectangle;

		public Jauge(Color fond, Color jauge, Color jauge_contour) {
			fond_couleur = fond;
			jauge_couleur = jauge;
			jauge_contour_couleur = jauge_contour;
			rectangle = new Rectangle();
			rectangle.width = this.getWidth();
			setFillParent(true);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

			//Fond
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(fond_couleur);
			shapeRenderer.rect(Vue.getAbsoluteX(this), Vue.getAbsoluteY(this), getWidth(), getHeight());
			shapeRenderer.end();

			//Jauge
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(jauge_couleur);
			shapeRenderer.rect(Vue.getAbsoluteX(this), Vue.getAbsoluteY(this), getWidth(), Math.max(0, rectangle.height));
			shapeRenderer.end();

			//Contour jauge
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			Gdx.gl20.glLineWidth(2 / camera.zoom);
			shapeRenderer.setColor(jauge_contour_couleur);
			shapeRenderer.rect(Vue.getAbsoluteX(this), Vue.getAbsoluteY(this), getWidth(), Math.max(0, rectangle.height));
			shapeRenderer.end();

			Gdx.gl.glDisable(GL20.GL_BLEND);
			Gdx.gl20.glLineWidth(1 / camera.zoom);

			batch.begin();
			super.draw(batch, parentAlpha);
		}
	}

}
