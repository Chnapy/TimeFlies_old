/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import general.Tourable;

/**
 * vEntites.java
 *
 */
public class vEntites extends Group implements Tourable {

	private Array<vEntite> listEntites;

	public vEntites(Array<? extends Entite> entites, int width, int height, int x, int y) {
		setSize(width, height);
		setPosition(x, y);
		listEntites = new Array();
		entites.forEach(perso -> {
			vEntite vent = new vEntite(perso);
			perso.addObserver(vent);
			addActor(vent);
			listEntites.add(vent);
		});
		setTouchable(Touchable.disabled);
	}

	public void addEntite(Entite entite) {
		Gdx.app.postRunnable(() -> {
			vEntite vent = new vEntite(entite);
			entite.addObserver(vent);
			addActor(vent);
			listEntites.add(vent);
		});
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		listEntites.forEach(entite -> {
			entite.nouveauTour(controleur, entiteEnCours, objs);
		});
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		listEntites.forEach(entite -> {
			entite.finTour(controleur, entiteEnCours, objs);
		});
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		listEntites.forEach(entite -> {
			entite.enTour(controleur, entiteEnCours, objs);
		});
	}

}
