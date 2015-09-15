/*
 * 
 * 
 * 
 */
package test;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.entite.Personnage;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import general.Orientation;

/**
 * Guerrier.java
 * CLASSE DE TEST
 *
 */
public class Guerrier2 extends Personnage {

	private static final int INDEX_TEXTURE = 1;
	private static final int INDEX_TEXTURE_TIMELINE = 1;

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
	public Guerrier2(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs) {

		super("Guerrier2", nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs, INDEX_TEXTURE, INDEX_TEXTURE_TIMELINE);
	}

}
