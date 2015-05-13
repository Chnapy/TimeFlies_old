/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;

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
	public boolean canDeclencher(Array<Effet> effets) {
		for (int i = 0; i < effets.size; i++) {
			if (declencheur.canDeclencher(effets.get(i), minimum, maximum)) {
				return true;
			}
		}
		return false;
	}

}
