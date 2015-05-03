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
public abstract class Zone implements Comparable<Zone> {

	private boolean positive;
	protected final int size;

	/**
	 *
	 * @param posit
	 * @param size
	 * @param currentMap
	 */
	public Zone(boolean posit, int size) {
		positive = posit;
		this.size = size * 2 + 1;
	}

	/**
	 * Make sure that a value is in bound.
	 *
	 * @param value - Value.
	 * @param max   - Maximum value.
	 * @return - In bound value.
	 */
//	protected static int valueInBound(int value, int max) {
//		if (value > max) {
//			return max;
//		} else if (value < 0) {
//			return 0;
//		} else {
//			return value;
//		}
//	}
	
	public abstract boolean[][] getZoneOfInterest();

//	public abstract Tuile[] getTilesOfInterrest(Point center);

	public boolean isPositive() {
		return this.positive;
	}

	@Override
	public int compareTo(Zone o) {
		if (o.isPositive()) {
			return 1;
		}
		return -1;
	}

}
