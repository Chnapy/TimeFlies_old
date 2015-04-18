/*
 * 
 * 
 * 
 */
package test;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.entite.Personnage;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

/**
 * Guerrier.java
 * CLASSE DE TEST
 * 
 */
public class Guerrier extends Personnage {

	/**
	 *
	 * @param nom
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param cPhysique
	 * @param sortsPassifs
	 * @param sortsActifs
	 */
	public Guerrier(String nom, 
			int posX, int posY, Orientation orientation, 
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs) {
		
		super(nom, "Guerrier", posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs);
	}

}
