package gameplay.effet;

import gameplay.entite.Entite;

public interface Declencheur {

	/**
	 * Permet de savoir si un effet peut déclancher un autre effet
	 * (envoutements/sorts passifs)
	 *
	 * @param effet
	 * @param min
	 * @param max
	 * @return true si le déclancheur peut avoir lieu
	 */
	public boolean canDeclencher(Effet effet, int min, int max);

	public boolean equals(Object o);

	/**
	 * Réalise l'effet sur la cible
	 * ex: balus de +5 en Vitalite
	 * Change la vie de la victime en ajoutant 5
	 *
	 * @param cible
	 * @param pourcentageSupp
	 */
	public void lancer(Entite cible, int pourcentageSupp);
}
