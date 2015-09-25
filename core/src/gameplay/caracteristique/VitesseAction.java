/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * VitesseAction.java
 * Gère la vitesse d'action de l'entité.
 * Varie entre 10 et 190 et se compte en pourcentage.
 * La vitesse d'action fait varier le temps d'action demandé par les actions.
 * Une vitesse de 10% augmentera le temps d'action nécessaire de 90%.
 * Une vitesse de 190% réduira le temps d'action nécessaire de 90%.
 * Une vitesse de 100% n'affectera pas le temps d'action nécessaire.
 *
 */
public class VitesseAction extends Caracteristique {

	/**
	 *
	 * @param actu
	 */
	public VitesseAction(int actu) {
		super(190, actu);
	}

	/**
	 * equals en fontion du type de la classe (VitesseAction)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof VitesseAction;
	}

}
