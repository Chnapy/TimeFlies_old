/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import general.Orientation;

/**
 * Personnage.java
 * Représente un personnage controlé par un joueur.
 *
 */
public abstract class Personnage extends EntiteActive {

	private String nomDonne;

	/**
	 *
	 * @param nom	             nom du personnage
	 * @param nomDonne	        nom donné par le joueur
	 * @param posX	            position en X
	 * @param posY	            position en Y
	 * @param orientation	     orientation du personnage
	 * @param cPhysique	       caractéristiques physiques du personnage
	 * @param sortsPassifs	    sorts passifs du personnages
	 * @param sortsActifs	     sorts actifs du personnages
	 * @param indexTexture
	 * @param iTextureTimeline
	 */
	public Personnage(String nom, String nomDonne,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs,
			int indexTexture,
			int iTextureTimeline) {
		super(nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs, indexTexture, iTextureTimeline);

		this.nomDonne = nomDonne;
		// TODO calculer le ratio : nbVictoire/nbPartieJouer*facteur
		niveauSymbol.add(1);
	}

}
