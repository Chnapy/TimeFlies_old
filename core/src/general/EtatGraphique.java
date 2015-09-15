/*
 * 
 * 
 * 
 */

package general;

/**
 * EtatGraphique
 * Enum
 */
public enum EtatGraphique {
	STAY("stay", 0),
	WALK("walk", 1),
	ATTACK("attack", 2),
	DIE("die", 3),
	HURT("hurt", 4);

	private final String tostring;
	public final int tabIndex;

	EtatGraphique(String _tostring, int _tabIndex) {
		tostring = _tostring;
		tabIndex = _tabIndex;
	}

	@Override
	public String toString() {
		return tostring;
	}

}
