/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import java.awt.*;

import gameplay.effet.Declencheur;

/**
 * Invocation.java
 * Gère les fonctions de l'invocation.
 *
 */
public interface Invocation extends Declencheur {

	public abstract boolean equals(Object o);
	
	public abstract void invoquer(Point point);
}
