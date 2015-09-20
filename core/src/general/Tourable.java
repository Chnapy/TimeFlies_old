/*
 * 
 * 
 * 
 */

package general;

import controleur.Controleur;
import gameplay.entite.EntiteActive;

/**
 * Tourable
 * Interface
 */
public interface Tourable {
	
	//Lancé lorsqu'un tour d'une entité commence
	public void nouveauTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs);

	//Lancé lorsqu'un tour d'une entité finit
	public void finTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs);

	//Lancé à chaque frame pendant le tour d'une entité
	public void enTour(Controleur controleur, EntiteActive entiteEnCours, Object... objs);

}
