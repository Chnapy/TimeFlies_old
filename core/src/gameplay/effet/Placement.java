/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.entite.Entite;

/**
 * Placement.java
 * Gère les mouvements et autres modifications de placement.
 *
 */
public abstract class Placement implements Declencheur {

	/**
	 *
	 */
	public Placement() {
	}

	/**
	 * Equals en fonction de la caracSpatiale
	 */
	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract boolean canDeclencher(Effet effet, int min, int max);

	@Override
	public abstract void lancer(Entite victime, int pourcentage);

}
