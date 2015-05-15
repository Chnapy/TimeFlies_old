/*
 * 
 * 
 * 
 */
package vue.hud.pileactions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import gameplay.entite.EntiteActive;

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

	private final ShapeRenderer shapeRenderer;
	private final Array<vAction> listActions;

	private int tempsActionMax;
	private int tempsActionActu;
	private int tempsActionDepense;
	private boolean actionEnCours;

	public vPileActions() {
//		debugAll();
		shapeRenderer = new ShapeRenderer();
		setPosition(X, Y);
		setSize(WIDTH, HEIGHT);
		listActions = new Array<>();
		tempsActionDepense = 0;
		align(Align.top);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		//Shape
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		if (!shapeRenderer.getProjectionMatrix().equals(batch.getProjectionMatrix())) {
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		}
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(228 / 255f, 228 / 255f, 228 / 255f, 1f);
		shapeRenderer.rect(X, Y, LEFT_WIDTH, HEIGHT);
		shapeRenderer.setColor(255 / 255f, 153 / 255f, 0 / 255f, 0.50f);
		shapeRenderer.rect(X + 1, Y + 1, LEFT_WIDTH - 2, (HEIGHT - 2) * tempsActionActu / tempsActionMax);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

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
		vAction nouveau = new vAction(shapeRenderer, indexTexture, tempsAction);
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
