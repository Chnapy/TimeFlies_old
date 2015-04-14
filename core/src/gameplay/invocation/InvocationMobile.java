/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.entite.EntiteActive;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

/**
 * InvocationMobile.java
 * Représente une invocation pouvant être controlée par un joueur.
 *
 */
public class InvocationMobile extends EntiteActive implements Invocation {

	/**
	 *
	 * @param nom
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param sortsPassifs
	 * @param sortsActifs
	 * @param cPhysique
	 */
	public InvocationMobile(String nom,
			int posX, int posY, Orientation orientation,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs,
			CaracteristiquePhysique cPhysique) {

		super(nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs);

	}

}
