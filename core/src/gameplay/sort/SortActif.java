/*
 * 
 * 
 * 
 */
package gameplay.sort;

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
	 * @param zportee
	 * @param zaction
	 */
	public SortActif(String nom, String description, Niveau niveau,
			ZonePortee zportee, ZoneAction zaction) {

		super(nom, description, niveau);

		zonePortee = zportee;
		zoneAction = zaction;
	}
	
}
