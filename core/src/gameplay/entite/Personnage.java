/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.utils.Array;
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
	 */
	public Personnage(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs) {

		super(nom, posX, posY, orientation, cPhysique, sortsPassifs, sortsActifs);

		niveauSymbol = new NiveauSymbolique(sortsPassifs, sortsActifs);
	}

}
