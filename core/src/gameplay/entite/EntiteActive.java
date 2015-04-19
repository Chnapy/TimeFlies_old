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

	/**
	 * Défini les actions que cette entité va effectuer lorsque chaque tour
	 * global commencera.
	 */
	public void debutTourGlobal() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionDebutTourGlobal();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chaque tour
	 * global se terminera.
	 */
	public void finTourGlobal() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionFinTourGlobal();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chacun de ses
	 * tours commencera.
	 */
	public void debutTour() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionDebutTour();
		}
	}

	/**
	 * Défini les actions que cette entité va effectuer lorsque chacun de ses
	 * tours finira.
	 */
	public void finTour() {
		for (Envoutement envout : listEnvoutements) {
			envout.actionFinTour();
		}
	}

	public CaracteristiquePhysique getCaracPhysique() {
		return caracPhysique;
	}

}
