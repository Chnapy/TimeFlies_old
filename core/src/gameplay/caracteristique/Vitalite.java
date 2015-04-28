/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Vitalite.java
 * Gère la vitalité de l'entité.
 *
 */
public class Vitalite extends Caracteristique {

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public Vitalite(int total, int actu) {
		super(total, actu);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Vitalite;
	}
}
