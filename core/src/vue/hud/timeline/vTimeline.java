/*
 * 
 * 
 * 
 */
package vue.hud.timeline;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import controleur.Controleur;
import gameplay.entite.EntiteActive;
import general.Tourable;
import static test.MainTest.MAX_HEIGHT;
import vue.Couleur;
import vue.hud.Bloc;
import vue.hud.bulle.BulleListener;

/**
 * vTimeline.java
 *
 */
public class vTimeline extends Bloc implements Tourable {

	//Couleurs de fond
	private static final Color FOND_COULEUR = Couleur.get("fond", "hud", "timeline");
	private static final Color FOND_CONTOUR_COULEUR = Couleur.get("fond_contour", "hud", "timeline");

	//Position et taille de la timeline
	private static final int X = 20;
	private static final int WIDTH = 1880;
	private static final int HEIGHT = 92;
	private static final int Y = MAX_HEIGHT - HEIGHT - 12;

	//Vue des entités sur la timeline
	private final Array<vTimelineEntite> listEntite;

	public vTimeline(final Array<? extends EntiteActive> listEntites) {
		super("Timeline", WIDTH, HEIGHT);
		setPosition(X, Y - getPadTop());
		listEntite = new Array<vTimelineEntite>();
		vTimelineEntite temp;
		for (int i = 0; i < listEntites.size; i++) {
			temp = new vTimelineEntite(listEntites.get(i), i);
			addActor(temp);
			listEntite.add(temp);
		}
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "La timeline liste les entite de la partie dans l'ordre.\nElle montre aussi leurs caracteristiques.";
			}
		});
	}

	@Override
	public void nouveauTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
		listEntite.forEach((entite) -> {
			entite.nouveauTour(controleur, entiteEnCours, listEntite.size - 1);
		});
	}

	@Override
	public void enTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void finTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

}
