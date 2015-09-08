/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import gameplay.sort.zone.ZoneAction;

/**
 * SortActif.java
 * Représente un sort pouvant être lancé par une entité active.
 *
 */
public abstract class SortActif extends Sort {

	//Zone de portée
	private ZoneAction zonePortee;

	//Zone d'action
	private ZoneAction zoneAction;

	//Temps d'action demandé pour l'exécution du sort
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
	 * @param tempsAction
	 */
	public SortActif(String nom, String description, Niveau niveau,
			Effet[] effets,
			ZoneAction zportee, ZoneAction zaction,
			int index, int tempsAction) {

		super(nom, description, niveau, effets, index);

		zonePortee = zportee;
		zoneAction = zaction;
		this.tempsAction = tempsAction;
	}

	/**
	 * lance le sort sur la victime check ses passif et
	 * renvoi les effets des passifs au lanceur si effectif
	 *
	 * @param cibleEntite
	 * @param cibleTuile
	 * @param lanceur
	 * @param oriAttaque
	 * @param critique
	 */
	public void lancerSort(Entite cibleEntite, Tuile cibleTuile, EntiteActive lanceur, Orientation oriAttaque, boolean critique) {
		cibleEntite.recoitSort(getTabEffets(), lanceur, oriAttaque, critique);
		cibleTuile.recoitSort(getTabEffets(), lanceur, oriAttaque, critique);
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
