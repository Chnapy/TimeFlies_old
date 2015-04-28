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
 *
 */
public class Declenchable {

	private Declencheur declencheur;
	private int minimum;
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
	 * Défini si les effets activent le déclencheur
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
