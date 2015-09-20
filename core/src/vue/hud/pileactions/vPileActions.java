/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import controleur.Controleur;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;
import vue.hud.vHud;

/**
 * vPileActions.java
 *
 */
public class vPileActions extends Bloc implements Tourable {

	//Position et taille de la pile d'actions
	private static int X = 20;
	private static int Y = 700;
	private static int WIDTH = 100;
	private static int HEIGHT = 200;
	private static int LEFT_WIDTH = 50;
	private static Color FOND_COULEUR = Couleur.get("fond", "hud", "pile_action");
	private static Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "pile_action");
	private static Color JAUGE_COULEUR = Couleur.get("jauge", "hud", "pile_action");

	private final Array<vAction> listActions;

	private int tempsActionMax;
	private int tempsActionActu;
	private int tempsActionDepense;
	private boolean actionEnCours;

	public vPileActions() {
		super("Pile d'actions", WIDTH, HEIGHT);
		setPosition(X, Y);
		listActions = new Array<>();
		tempsActionDepense = 0;
		align(Align.topLeft);
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "La pile d'actions affiche en temps reel votre temps d'action et les differentes actions que vous effectuez.";
			}
		});
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
		if (tempsActionMax > 0) {
			//Shape
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			vHud.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			vHud.shapeRenderer.setColor(JAUGE_COULEUR);
			vHud.shapeRenderer.rect(getX() + 1, getY() + 1, LEFT_WIDTH - 1, (HEIGHT - 2) * tempsActionActu / tempsActionMax);
			vHud.shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}

	public void addAction(int indexTexture, int tempsAction) {
		if (!actionEnCours) {
			add().bottom().height(HEIGHT * (tempsActionMax - tempsActionActu - tempsActionDepense) / tempsActionMax).row();
			tempsActionDepense += tempsActionMax - tempsActionActu - tempsActionDepense;
		}
		tempsActionDepense += tempsAction;
		vAction nouveau = new vAction(indexTexture, tempsAction);
		listActions.add(nouveau);
		add(nouveau.getTempsAction()).left().width(LEFT_WIDTH).height(HEIGHT * tempsAction / tempsActionMax).expandX();
		add(nouveau.getIconeAction()).right().bottom()
				.maxWidth(LEFT_WIDTH).prefWidth(HEIGHT * tempsAction / tempsActionMax)
				.maxHeight(LEFT_WIDTH).prefHeight(HEIGHT * tempsAction / tempsActionMax);
		row();
		actionEnCours = true;
	}

	public void delAction() {
		listActions.removeIndex(0);
		if (listActions.size == 0) {
			actionEnCours = false;
		}
	}

	@Override
	public void nouveauTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		int tempsAction = entiteEnCours.getTempsAction().getActu();
		listActions.clear();
		getCells().clear();
		while (getChildren().size > 1) {
			getChildren().removeIndex(1);
		}
		tempsActionMax = tempsActionActu = tempsAction;
		tempsActionDepense = 0;
	}

	@Override
	public void enTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		tempsActionActu = entiteEnCours.getTempsAction().getActu();
		if (listActions.size > 0) {
			if (!listActions.first().getTempsAction().actu()) {
				delAction();
			}
		}
	}

	@Override
	public void finTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
	}
}
