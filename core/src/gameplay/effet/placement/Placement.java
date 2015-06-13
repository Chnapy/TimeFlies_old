/*
 * 
 * 
 * 
 */
package gameplay.effet.placement;

import gameplay.caracteristique.Orientation;
import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
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
	 *
	 * @return
	 */
	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract boolean canDeclencher(Effet effet, int min, int max);

	@Override
	public abstract void lancerEntite(Entite victime, Orientation oriLanceur, boolean ccritique);

}
