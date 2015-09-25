/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * Vitalite.java
 * Gère la vitalité de l'entité.
 * Si la vitalité devient nulle ou inférieur à 0, l'entité devient à terre.
 *
 */
public class Vitalite extends Caracteristique {

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public Vitalite(int total) {
		super(total, total);
	}

	/**
	 * equals en fontion du type de la classe (Vitalité)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof Vitalite;
	}
}
