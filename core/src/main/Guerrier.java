/*
 * 
 * 
 * 
 */
package main;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.entite.NiveauSymbolique;
import gameplay.entite.Personnage;

/**
 * Guerrier.java
 * CLASSE DE TEST
 * 
 */
public class Guerrier extends Personnage {

	public Guerrier(String nom, 
			int posX, int posY, Orientation orientation, 
			CaracteristiquePhysique cPhysique, 
			NiveauSymbolique nsymbol) {
		
		super(nom, posX, posY, orientation, cPhysique, nsymbol);
	}

}
