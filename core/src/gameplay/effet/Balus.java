/*
 * 
 * 
 * 
 */
package gameplay.effet;

import gameplay.caracteristique.Carac;
import gameplay.caracteristique.Orientation;
import gameplay.entite.Entite;
import gameplay.map.Tuile;

/**
 * Balus.java
 * Gère les bonus et malus dans une caractéristique au choix.
 *
 */
public class Balus implements Declencheur {

	//Caractéristique ciblée
	private Carac caracteristique;

	//Valeur du bonus/malus
	private int valeur;

	/**
	 *
	 * @param caracteristique
	 * @param nombre
	 */
	public Balus(Carac caracteristique, int nombre) {
		this.caracteristique = caracteristique;
		this.valeur = nombre;
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
	 *
	 * @return
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
		return caracteristique == other.caracteristique;
	}

	/**
	 * Définit si l'effet peut déclencher l'effet d'un sort passif ou
	 * envoutement
	 *
	 * @param effet
	 * @param min
	 * @param max
	 * @return
	 */
	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getDeclencheur().size; i++) {
			if (effet.getDeclencheur().get(i).equals(this)) {
				Balus balus = (Balus) effet.getDeclencheur().get(i);
				if (balus.getValeur() <= min && balus.getValeur() >= max) {
					return true;
				}
			}
		}
		return false;
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
	 * @return le valeur à enlever/ajouter
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Applique l'effet à l'entité cible
	 *
	 * @param cible
	 */
	@Override
	public void lancerEntite(Entite cible, Orientation oriLanceur, boolean ccritique) {
		cible.getCaracPhysique().add(caracteristique, ccritique ? valeur : valeur + (30 * valeur / 100));
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
	}

}
