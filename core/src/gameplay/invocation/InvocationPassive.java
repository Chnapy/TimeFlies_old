/*
 * 
 * 
 * 
 */
package gameplay.invocation;

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
	 */
	public InvocationPassive(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs) {

		super(n, posX, posY, orient, sortsPassifs);
	}

}
