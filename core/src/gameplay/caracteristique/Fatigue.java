/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Fatigue.java
 * Gère la fatigue de l'entité.
 *
 */
public class Fatigue extends Caracteristique {

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public Fatigue(int total, int actu) {
		super(total, actu);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Fatigue;
	}

}
