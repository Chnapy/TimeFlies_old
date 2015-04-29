package gameplay.effet;

import gameplay.entite.Entite;

public interface Declencheur {

	/**
	 * permet de savoir si un éffet peu déclanger un autre effet
	 * 
	 * @param effet
	 * @param min
	 * @param max
	 * @return true si le déclancheur peu avoir lieu
	 */
	public boolean canDeclencher(Effet effet, int min, int max);
	
	public boolean equals(Object o);
	
	/**
	 * réalise l'effet sur la victime 
	 * ex: balus de +5 en Vitalite
	 * change la vie de la victime en ajoutant 5
	 * @param victime
	 */
	public void lancer(Entite victime);
}
