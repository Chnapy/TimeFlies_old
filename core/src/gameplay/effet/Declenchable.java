/*
 * 
 * 
 * 
 */
package gameplay.effet;

/**
 * Declenchable.java
 * Représente la condition pour qu'un effet se déclenche.
 * Est utilisé dans les sorts passifs et envoutements.
 *
 */
public class Declenchable {

	//Déclencheur comparable
	private Declencheur declencheur;

	//Valeur minimum du déclencheur
	private int minimum;

	//Valeur maximum du déclencheur
	private int maximum;

	/**
	 *
	 * @param declencheur
	 * @param min
	 * @param max
	 */
	public Declenchable(Declencheur declencheur, int min, int max) {
		this.declencheur = declencheur;
		minimum = min;
		maximum = max;
	}

	/**
	 * Défini si au moins un des effets active le déclencheur.
	 *
	 * @param effets
	 * @return true si un des effets activent le déclencheur, sinon false
	 */
	public boolean canDeclencher(Effet[] effets) {
		for (Effet effet : effets) {
			if (declencheur.canDeclencher(effet, minimum, maximum)) {
				return true;
			}
		}
		return false;
	}

}
