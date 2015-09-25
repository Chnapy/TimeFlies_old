/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * TempsAction.java
 * Gère le temps d'action de l'entité.
 * Permet l'utilisation d'action (sorts/déplacements)
 * Dépend de la fatigue.
 * Se compte en milisecondes (1s = 1000ms)
 *
 */
public class TempsAction extends Caracteristique {

	/**
	 * c'est le temps défini de base pour le calcule de la fatigue
	 */
	private int tempsBase;

	/**
	 *
	 * @param total	en ms
	 */
	public TempsAction(int total) {
		super(total, total);
		this.tempsBase = total;
	}

	public int getTempsBase() {
		return tempsBase;
	}

	/**
	 * equals en fontion du type de la classe (TempsAction)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof TempsAction;
	}
}
