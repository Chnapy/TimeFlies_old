/*
 * 
 * 
 * 
 */
package vue.hud.timeline;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import general.Tourable;
import java.util.Arrays;
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
	private final Array<vTimelineEntite> listEntites;
	private int index;

	public vTimeline(final Array<Entite> entites, AssetManager manager) {
		super("Timeline", WIDTH, HEIGHT, manager);
		setPosition(X, Y - getPadTop());
		listEntites = new Array<vTimelineEntite>();
		index = 0;
		applyEntites(entites);
		addListener(new BulleListener(this) {

			@Override
			public String getBulleContent() {
				return "La timeline liste les entite de la partie dans l'ordre.\nElle montre aussi leurs caracteristiques.";
			}
		});
	}

	public void addEntite(Entite entite) {
		if(entite instanceof EntiteActive) {
			applyEntites(Array.with(entite));
		}
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		listEntites.forEach((entite) -> {
			entite.nouveauTour(controleur, entiteEnCours, listEntites.size - 1);
		});
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
	}
	
	private void applyEntites(Array<Entite> entites) {
		int listSize = listEntites.size;
		while (index < listSize + entites.size) {
			if (entites.get(index - listSize) instanceof EntiteActive) {
				vTimelineEntite temp = new vTimelineEntite((EntiteActive) entites.get(index - listSize), index, manager);
				addActor(temp);
				listEntites.add(temp);
				index++;
			}
		}
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

}
