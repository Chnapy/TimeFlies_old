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

	/**
	 * equals en fontion du type de la classe (Vitalité)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof Vitalite;
	}
}
