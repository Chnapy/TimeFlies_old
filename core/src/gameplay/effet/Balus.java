/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.caracteristique.Carac;

/**
 * Balus.java
 * Gère les bonus et malus.
 *
 */
public class Balus {

	private Carac caracteristique;
	private int nombre;

	/**
	 *
	 * @param cphysique
	 */
	public Balus(Carac caracteristique, int nombre) {
		this.caracteristique = caracteristique;
		this.nombre = nombre;
	}

	public Carac getCaracteristique() {
		return caracteristique;
	}

	public int getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caracteristique == null) ? 0 : caracteristique.hashCode());
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
		if (caracteristique != other.caracteristique)
			return false;
		return true;
	}

}
