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

/**
 * SortActif.java
 * Représente un sort pouvant être lancé par une entité active.
 *
 */
public abstract class SortActif extends Sort {

	private ZoneAction zonePortee;
	private ZoneAction zoneAction;
	private int tempsAction;

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
			ZoneAction zportee, ZoneAction zaction,
			int index,int tempsAction) {

		super(nom, description, niveau, effets, index);

		zonePortee = zportee;
		zoneAction = zaction;
		this.tempsAction = tempsAction;
	}

	/**
	 * lance le sort sur la victime check ses passif et
	 * renvoi les effets des passifs au lanceur si effectif
	 *
	 * @param cible
	 * @param lanceur
	 */
	public void lancerSort(Entite cible, EntiteActive lanceur) {
		cible.recoitSort(getTabEffets(), lanceur);
	}

	public int getTempsAction() {
		return tempsAction;
	}

	public ZoneAction getZonePortee() {
		return zonePortee;
	}

	public ZoneAction getZoneAction() {
		return zoneAction;
	}

}
