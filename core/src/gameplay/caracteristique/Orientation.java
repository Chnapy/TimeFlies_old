/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Orientation.java
 * Orientation de l'entité.
 *
 *
 */
public enum Orientation {

	/**
	 * Nord (Haut-Gauche)
	 */
	N,
	/**
	 * Est (Haut-Droite)
	 */
	E,
	/**
	 * Sud (Bas-Droite)
	 */
	S,
	/**
	 * Ouest (Bas-Gauche)
	 */
	O;

	/**
	 * Renvoie l'inverse de l'orientation actuelle
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
