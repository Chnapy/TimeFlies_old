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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;
import vue.Couleur;
import vue.hud.vHud;

/**
 * vPileActions.java
 *
 */
public class vPileActions extends Table {

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
//		debugAll();
		setPosition(X, Y);
		setSize(WIDTH, HEIGHT);
		listActions = new Array<>();
		tempsActionDepense = 0;
		align(Align.top);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		vHud.drawBackground(X, Y, LEFT_WIDTH, HEIGHT, FOND_COULEUR, FOND_CONTOUR_COULEUR);

		if (tempsActionMax > 0) {
			//Shape
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			vHud.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			vHud.shapeRenderer.setColor(JAUGE_COULEUR);
			vHud.shapeRenderer.rect(X + 1, Y + 1, LEFT_WIDTH - 2, (HEIGHT - 2) * tempsActionActu / tempsActionMax);
			vHud.shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}

		//Batch
		batch.begin();
		super.draw(batch, parentAlpha);
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

	public void nouveauTour(int tempsAction) {
		listActions.clear();
		clear();
		tempsActionMax = tempsActionActu = tempsAction;
		tempsActionDepense = 0;
	}

	public void tourEnCours(final EntiteActive entiteEnCours) {
		tempsActionActu = entiteEnCours.getTempsAction().getActu();
		if (listActions.size > 0) {
			if (!listActions.first().getTempsAction().actu()) {
				delAction();
			}
		}
	}
}
