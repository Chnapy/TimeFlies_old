/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Orientation.java
 * Orientation de l'entité.
 */
public enum Orientation {

	/**
	 *
	 */
	N,
	/**
	 *
	 */
	E,
	/**
	 *
	 */
	S,
	/**
	 *
	 */
	O;

	/**
	 *
	 * @return l'inverse de l'orientation
	 */
	public Orientation invert() {
		switch (this) {
			case N:
				return S;
			case E:
				return O;
			case S:
				return N;
			case O:
				return E;
			default:
				return N;
		}
	}
}
