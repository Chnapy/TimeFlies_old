/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.caracteristique.Carac;
import gameplay.entite.Entite;

/**
 * Balus.java
 * Gère les bonus et malus.
 *
 */
public class Balus implements Declencheur {

	private Carac caracteristique;
	private int nombre;

	/**
	 *
	 * @param caracteristique
	 * @param nombre
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Balus other = (Balus) obj;
		if (caracteristique != other.caracteristique) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getDeclencheur().size; i++) {
			if (effet.getDeclencheur().get(i).equals(this)) {
				Balus balus = (Balus) effet.getDeclencheur().get(i);
				if (balus.getNombre() <= min && balus.getNombre() >= max) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void lancer(Entite victime, int pourcentagesupp) {
		victime.getCaracPhysique().add(caracteristique, nombre + (pourcentagesupp * nombre / 100));
	}

}
