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
public abstract class Personnage extends EntiteActive {

	private String nomDonne;
	private NiveauSymbolique niveauSymbol;

	/**
	 *
	 * @param nom	nom du personnage
	 * @param nomDonne	nom donné par le joueur
	 * @param posX	position en X
	 * @param posY	position en Y
	 * @param orientation	orientation du personnage
	 * @param cPhysique	caractéristiques physiques du personnage
	 * @param sortsPassifs	sorts passifs du personnages
	 * @param sortsActifs	sorts actifs du personnages
	 */
	public Personnage(String nom, String nomDonne,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs) {
		super(nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs);
		
		this.nomDonne = nomDonne;
		niveauSymbol = new NiveauSymbolique(sortsPassifs, sortsActifs);
	}

}
