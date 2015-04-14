/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.envoutement.Envoutement;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

/**
 * EntiteActive.java
 * Représente une entité active (controlable par un joueur).
 *
 */
public abstract class EntiteActive extends Entite {

	private final CaracteristiquePhysique caracPhysique;
	private SortActif[] tabSortActif;
	private Array<Envoutement> listEnvoutements;

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
	public EntiteActive(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique,
			SortPassif[] sortsPassifs,
			SortActif[] sortsActifs) {

		super(nom, posX, posY, orientation, sortsPassifs);

		caracPhysique = cPhysique;
		tabSortActif = sortsActifs;
		listEnvoutements = new Array<Envoutement>();
	}

}
