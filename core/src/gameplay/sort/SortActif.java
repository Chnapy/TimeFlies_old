/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.caracteristique.Carac;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import gameplay.sort.zone.ZoneAction;
import general.Orientation;
import general.TypeDonnee;

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

	//Nombre de tours avant réutilisation possible
	private int cooldown;
	
	private int fatigue;

	//Cooldown actuel
	protected int cooldownActu;

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
	 * @param _cooldown
	 * @param _fatigue
	 */
	public SortActif(String nom, String description, Niveau niveau,
			Effet[] effets,
			ZoneAction zportee, ZoneAction zaction,
			int index, int tempsAction, int _cooldown, int _fatigue) {

		super(nom, description, niveau, effets, index);

		zonePortee = zportee;
		zoneAction = zaction;
		this.tempsAction = tempsAction;
		cooldown = _cooldown;
		cooldownActu = 0;
		fatigue = _fatigue;
	}

	@Override
	public void lancerSort(Entite cibleEntite, Tuile cibleTuile, EntiteActive lanceur, Orientation oriAttaque, boolean critique) {
		super.lancerSort(cibleEntite, cibleTuile, lanceur, oriAttaque, critique);
		if(fatigue > 0) {
			lanceur.getCaracPhysique().add(Carac.FATIGUE, fatigue);
		}
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

	public int getCooldown() {
		return cooldown;
	}

	public int getCooldownActu() {
		return cooldownActu;
	}

	public void setCooldownActu(int _cooldownactu) {
		cooldownActu = _cooldownactu;
		setChanged();
		notifyObservers(new Object[]{
			TypeDonnee.COOLDOWN,
			cooldownActu
		});
	}

	public void resetCooldown() {
		setCooldownActu(cooldown);
	}

	public void subCooldown() {
		if (cooldownActu > 0) {
			setCooldownActu(cooldownActu - 1);
		}
	}

}
