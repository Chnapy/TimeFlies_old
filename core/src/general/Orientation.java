package general;

/*
 * 
 * 
 * 
 */
/**
 * Orientation.java
 * Enumeration
 */
public enum Orientation {

	NORD('N', "Nord", 0),
	SUD('S', "Sud", 1),
	OUEST('O', "Ouest", 2),
	EST('E', "Est", 3);

	public final char min;
	private final String tostring;
	public final int tabIndex;

	Orientation(char _min, String _tostring, int _tabIndex) {
		min = _min;
		tostring = _tostring;
		tabIndex = _tabIndex;
	}

	/**
	 * Renvoie l'inverse de l'orientation actuelle
	 *
	 * @return l'inverse de l'orientation
	 */
	public Orientation invert() {
		switch (this) {
			case NORD:
				return SUD;
			case EST:
				return OUEST;
			case SUD:
				return NORD;
			case OUEST:
				return EST;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return tostring;
	}

}
