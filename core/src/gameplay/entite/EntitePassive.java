/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.sort.SortPassif;

/**
 * EntitePassive.java
 * Représente une entité passive (autonome).
 *
 */
public abstract class EntitePassive extends Entite {

	/**
	 *
	 * @param n
	 * @param posX
	 * @param posY
	 * @param orient
	 * @param sortsPassifs
	 * @param cPhysique
	 */
	public EntitePassive(String n,
			int posX, int posY, Orientation orient,
			SortPassif[] sortsPassifs, CaracteristiquePhysique cPhysique) {

		super(n, posX, posY, orient, sortsPassifs, cPhysique);
		this.niveauSymbol = new NiveauSymbolique(sortsPassifs);
	}

	public EtatEntite getEtatNow() {
		return getEtat();
	}

}
