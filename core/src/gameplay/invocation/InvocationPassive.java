/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import gameplay.caracteristique.Orientation;
import gameplay.entite.EntitePassive;

/**
 * InvocationPassive.java
 * Représente une invocation autonome.
 *
 */
public class InvocationPassive extends EntitePassive implements Invocation {

	public InvocationPassive(String n,
			int posX, int posY, Orientation orient) {

		super(n, posX, posY, orient);
	}

}
