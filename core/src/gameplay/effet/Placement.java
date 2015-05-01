/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.caracteristique.CaracteristiqueSpatiale;
import gameplay.entite.Entite;

/**
 * Placement.java
 * Gère les mouvements et autres modifications de placement.
 *
 */
public class Placement implements Declencheur {

	private CaracteristiqueSpatiale caracSpatiale;

	/**
	 *
	 * @param cspatiale
	 */
	public Placement(CaracteristiqueSpatiale cspatiale) {
		caracSpatiale = cspatiale;
	}

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caracSpatiale == null) ? 0 : caracSpatiale.hashCode());
		return result;
	}

	/**
	 * Equals en fonction de la caracSpatiale
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Placement other = (Placement) obj;
		if (caracSpatiale == null) {
			if (other.caracSpatiale != null) {
				return false;
			}
		} else if (!caracSpatiale.equals(other.caracSpatiale)) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * @return la caractéristique spatiale
	 */
	public CaracteristiqueSpatiale getCaracSpatiale() {
		return caracSpatiale;
	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getDeclencheur().size; i++) {
			if (effet.getDeclencheur().get(i) instanceof Placement) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void lancer(Entite victime) {
		victime.setCaracSpatiale(caracSpatiale);
	}

}
