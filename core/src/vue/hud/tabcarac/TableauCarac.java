/*
 * 
 * 
 * 
 */
package vue.hud.tabcarac;

import com.badlogic.gdx.graphics.g2d.Batch;
import controleur.Controleur;
import gameplay.entite.EntiteActive;
import general.Tourable;
import vue.hud.Bloc;

/**
 * TableauCarac.java
 * 
 */
public class TableauCarac extends Bloc implements Tourable {

	//Position et taille de la timeline
	private static final int X = 20;
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final int Y = 350;

	public TableauCarac() {
		super("Données", WIDTH, HEIGHT);
		setPosition(X, Y);
	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

	@Override
	public void nouveauTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void finTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
	}

	@Override
	public void enTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs) {
	}

}
