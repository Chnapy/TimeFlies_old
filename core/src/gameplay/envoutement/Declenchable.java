/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

import gameplay.effet.Effet;

/**
 * Declenchable.java
 * Représente la condition pour qu'un effet se déclenche.
 *
 */
public class Declenchable {

	private Effet effet;
	private int minimum;
	private int maximum;

	/**
	 *
	 * @param ef
	 * @param min
	 * @param max
	 */
	public Declenchable(Effet ef, int min, int max) {
		effet = ef;
		minimum = min;
		maximum = max;
	}

}
