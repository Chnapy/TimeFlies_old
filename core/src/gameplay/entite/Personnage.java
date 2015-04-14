/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

/**
 * Personnage.java
 * Représente un personnage controlé par un joueur.
 *
 */
public class Personnage extends EntiteActive {

	private NiveauSymbolique niveauSymbol;

	/**
	 *
	 * @param nom
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param cPhysique
	 * @param sortsPassifs
	 * @param sortsActifs
	 * @param nsymbol
	 */
	public Personnage(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs,
			NiveauSymbolique nsymbol) {

		super(nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs);

		niveauSymbol = nsymbol;
	}

}
