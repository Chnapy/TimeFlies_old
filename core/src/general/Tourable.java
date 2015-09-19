/*
 * 
 * 
 * 
 */

package general;

import controleur.cCombat;
import gameplay.entite.EntiteActive;

/**
 * Tourable
 * Interface
 */
public interface Tourable {
	
	//Lancé lorsqu'un tour d'une entité commence
	public void nouveauTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs);

	//Lancé lorsqu'un tour d'une entité finit
	public void finTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs);

	//Lancé à chaque frame pendant le tour d'une entité
	public void enTour(cCombat controleur, EntiteActive entiteEnCours, Object... objs);

}
