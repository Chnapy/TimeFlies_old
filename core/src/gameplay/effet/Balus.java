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
public class Balus implements Declancher{

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

	/**
	 * 
	 * @return caracteristique
	 */
	public Carac getCaracteristique() {
		return caracteristique;
	}

	/**
	 * 
	 * @return le nombre à enlever/ajouter
	 */
	public int getNombre() {
		return nombre;
	}

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caracteristique == null) ? 0 : caracteristique.hashCode());
		return result;
	}

	/**
	 * equals en fonction de la caracteristique uniquement
	 */
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

	@Override
	public boolean canDeclanche(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getListBalus().size; i++) {
			if(effet.getListBalus().get(i).equals(this))
				if(effet.getListBalus().get(i).getNombre()<=min && effet.getListBalus().get(i).getNombre()>=max)
					return true;
		}
		return false;
	}

}
