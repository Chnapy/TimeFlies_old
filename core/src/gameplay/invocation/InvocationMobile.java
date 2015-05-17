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
public abstract class InvocationMobile extends EntiteActive implements Invocation {

	/**
	 *
	 * @param nom
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param sortsPassifs
	 * @param sortsActifs
	 * @param cPhysique
	 * @param indexTexture
	 * @param iTextureTimeline
	 */
	public InvocationMobile(String nom,
			int posX, int posY, Orientation orientation,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs,
			CaracteristiquePhysique cPhysique,
			int indexTexture,
			int iTextureTimeline) {

		super(nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs, indexTexture, iTextureTimeline);

	}

}
