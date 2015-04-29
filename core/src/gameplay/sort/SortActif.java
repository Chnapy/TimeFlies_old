/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
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
	 * @param index
	 */
	public SortActif(String nom, String description, Niveau niveau,
			Effet[] effets,
			ZonePortee zportee, ZoneAction zaction,
			int index) {

		super(nom, description, niveau, effets, index);

		zonePortee = zportee;
		zoneAction = zaction;
	}

	/**
	 * lance le sort sur la victime check ses passif et 
	 * renvoi les effets des passifs au lanceur si effectif
	 * @param victime
	 * @param lanceur
	 */
	public void lancerSort(Entite victime, EntiteActive lanceur) {
		victime.recoitSort(getTabEffets(),lanceur);
	}

}
