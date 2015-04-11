/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;

/**
 * Personnage.java
 * Représente un personnage controlé par un joueur.
 *
 */
public class Personnage extends EntiteActive {

	private NiveauSymbolique niveauSymbol;

	public Personnage(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			NiveauSymbolique nsymbol) {

		super(nom, posX, posY, orientation, cPhysique);

		niveauSymbol = nsymbol;
	}

}
