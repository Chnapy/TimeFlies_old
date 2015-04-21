/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.sort.zone.ZoneAction;
import gameplay.sort.zone.ZonePortee;

/**
 * SortActif.java
 * Représente un sort pouvant être lancé par une entité active.
 *
 */
public abstract class SortActif extends Sort {

	private ZonePortee zonePortee;
	private ZoneAction zoneAction;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param zportee
	 * @param zaction
	 */
	public SortActif(String nom, String description, Niveau niveau,
			Effet[] effets,
			ZonePortee zportee, ZoneAction zaction) {

		super(nom, description, niveau, effets);

		zonePortee = zportee;
		zoneAction = zaction;
	}

	public void lancerSort(Entite entite) {
		entite.recoitSort(getTabEffets());
	}

}
