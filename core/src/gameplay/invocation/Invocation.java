/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import gameplay.effet.Declencheur;
import java.awt.Point;

/**
 * Invocation.java
 * Gère les fonctions de l'invocation.
 *
 */
public interface Invocation extends Declencheur {

	public abstract boolean equals(Object o);

	public abstract void invoquer(Point point);
}
