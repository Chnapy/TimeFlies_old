/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

/**
 * Zone.java
 * Représente une zone pouvant être positive ou négative.
 * Une zone positive représente une zone classique.
 * Une zone négative fait un "trou" dans une zone positive.
 *
 */
public abstract class Zone {

	private boolean positive;

	/**
	 *
	 * @param posit
	 */
	public Zone(boolean posit) {
		positive = posit;
	}

}
