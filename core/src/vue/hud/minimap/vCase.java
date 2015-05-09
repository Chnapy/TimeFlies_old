/*
 * 
 * 
 * 
 */
package vue.hud.minimap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import controleur.cCombat;
import gameplay.map.EtatTuile;

/**
 * vCase.java
 *
 */
public class vCase extends Actor {

	private static final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private static boolean projectionMatrixSet;

	private final int x;
	private final int y;
	private EtatTuile etat;
	private boolean hover;
	private boolean action;
	private Color basic;
	private Color couleur;

	public vCase(cCombat controleur, int x, int y, int typeTuile, EtatTuile e) {
		this.x = x;
		this.y = y;
		etat = e;
		switch (typeTuile) {
			case 0:
				basic = Color.GRAY;
				break;
			case 1:
				basic = Color.BLACK;
				break;
			case 2:
				basic = Color.RED;
				break;
			case 3:
				basic = Color.valueOf("FF9253");
				break;
		}
		couleur = basic;
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float X, float Y,
					int pointer, int button) {
				controleur.clicSurTuile(x, y);	//Déplacement/Lancement de sort
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				setHover(false);
			}

			@Override
			public void enter(InputEvent event, float X, float Y, int pointer, Actor fromActor) {
				setHover(true);
				controleur.survolTuile(x, y);	//Affichage déplacement possible
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		if (!projectionMatrixSet) {
			projectionMatrixSet = true;
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		}
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
		shapeRenderer.setColor(couleur);
		shapeRenderer.rect(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
		shapeRenderer.end();
		batch.begin();
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

	private void setCouleur() {
		if (action) {
			couleur = Color.RED;
		} else if (!hover) {
			switch (etat) {
				case NORMAL:
					couleur = basic;
					break;
				case PATH:
					couleur = Color.YELLOW;
					break;
				case ZONESORT:
					couleur = Color.CYAN;
					break;
				default:
					throw new Error("Etat tuile non géré");
			}
		} else {
			switch (etat) {
				case NORMAL:
					couleur = Color.ORANGE;
					break;
				case PATH:
					break;
				case ZONESORT:
					couleur = Color.BLUE;
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

	public float getPosX() {
		return x;
	}

	public float getPosY() {
		return y;
	}

}
