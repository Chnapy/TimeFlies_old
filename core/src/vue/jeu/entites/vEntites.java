/*
 * 
 * 
 * 
 */
package vue.jeu.entites;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import controleur.ControleurPrincipal;
import gameplay.entite.EntiteActive;
import gameplay.entite.Personnage;
import general.Tourable;

/**
 * vEntites.java
 *
 */
public class vEntites extends Group implements Tourable {
	
	private Array<vEntite> entites;

	public vEntites(Array<Personnage> personnages) {
		entites = new Array();
		personnages.forEach(perso -> {
			vEntite vent = new vEntite(perso);
			perso.addObserver(vent);
			addActor(vent);
			entites.add(vent);
		});
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		entites.forEach(entite -> {
			entite.nouveauTour(controleur, entiteEnCours, objs);
		});
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		entites.forEach(entite -> {
			entite.finTour(controleur, entiteEnCours, objs);
		});
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		entites.forEach(entite -> {
			entite.enTour(controleur, entiteEnCours, objs);
		});
	}

}
