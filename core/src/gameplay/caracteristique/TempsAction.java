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
	 *
	 * @param total	en ms
	 * @param actu	 en ms
	 */
	public TempsAction(int total, int actu) {
		super(total, actu);
	}

	/**
	 * equals en fontion du type de la classe (TempsAction)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof TempsAction;
	}
}
