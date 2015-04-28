/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * VitesseAction.java
 * Gère la vitesse d'action de l'entité.
 *
 */
public class VitesseAction extends Caracteristique {

	/**
	 *
	 * @param total
	 * @param actu
	 */
	public VitesseAction(int total, int actu) {
		super(total, actu);
	}
	/**
	 * equals en fontion du type de la classe (VitesseAction)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof VitesseAction;
	}

}
