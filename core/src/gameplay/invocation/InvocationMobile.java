/*
 * 
 * 
 * 
 */
package gameplay.invocation;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.entite.EntiteActive;

/**
 * InvocationMobile.java
 * Représente une invocation pouvant être controlée par un joueur.
 *
 */
public class InvocationMobile extends EntiteActive implements Invocation {

	public InvocationMobile(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique) {

		super(nom, posX, posY, orientation, cPhysique);

	}

}
