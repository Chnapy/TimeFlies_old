/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import com.badlogic.gdx.math.GridPoint2;
import gameplay.effet.Declencheur;

/**
 * Invocation.java
 * Gère les fonctions de l'invocation.
 *
 */
public interface Invocation extends Declencheur {

	@Override
	public abstract boolean equals(Object o);

	/**
	 * Créé l'invocation au point donné
	 *
	 * @param point
	 */
	public abstract void invoquer(GridPoint2 point);
}
