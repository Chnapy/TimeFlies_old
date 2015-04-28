/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.caracteristique.CaracteristiqueSpatiale;

/**
 * Placement.java
 * Gère les mouvements et autres modifications de placement.
 *
 */
public class Placement {

	private CaracteristiqueSpatiale caracSpatiale;

	/**
	 *
	 * @param cspatiale
	 */
	public Placement(CaracteristiqueSpatiale cspatiale) {
		caracSpatiale = cspatiale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caracSpatiale == null) ? 0 : caracSpatiale.hashCode());
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
		Placement other = (Placement) obj;
		if (caracSpatiale == null) {
			if (other.caracSpatiale != null)
				return false;
		} else if (!caracSpatiale.equals(other.caracSpatiale))
			return false;
		return true;
	}

	public CaracteristiqueSpatiale getCaracSpatiale() {
		return caracSpatiale;
	}

	
}
