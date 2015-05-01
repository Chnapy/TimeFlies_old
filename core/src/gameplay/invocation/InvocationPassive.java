/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.entite.EntitePassive;
import gameplay.sort.SortPassif;

/**
 * InvocationPassive.java
 * Représente une invocation autonome.
 *
 */
public abstract class InvocationPassive extends EntitePassive implements Invocation {

	/**
	 *
	 * @param n
	 * @param posX
	 * @param posY
	 * @param orient
	 * @param sortsPassifs
	 * @param cphysique
	 */
	public InvocationPassive(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs, CaracteristiquePhysique cphysique) {

		super(n, posX, posY, orient, sortsPassifs, cphysique);
	}

}
