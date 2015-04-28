/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.caracteristique.CaracteristiquePhysique;

/**
 * Balus.java
 * Gère les bonus et malus.
 *
 */
public class Balus {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caracPhysique == null) ? 0 : caracPhysique.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Balus other = (Balus) obj;
		if (caracPhysique == null) {
			if (other.caracPhysique != null)
				return false;
		} else if (!caracPhysique.equals(other.caracPhysique))
			return false;
		return true;
	}

	private CaracteristiquePhysique caracPhysique;

	/**
	 *
	 * @param cphysique
	 */
	public Balus(CaracteristiquePhysique cphysique) {
		caracPhysique = cphysique;
	}

}
