/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * TempsSup.java
 * Gère le temps supplémentaire de l'entité.
 * Utilisé lorsqu'une action utilise plus que le temps d'action disponible.
 *
 */
public class TempsSup extends Caracteristique {

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public TempsSup(int total, int actu) {
		super(total, actu);
	}

	/**
	 * equals en fontion du type de la classe (TempsSup)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof TempsSup;
	}
}
