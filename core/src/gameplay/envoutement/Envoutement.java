/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import general.Orientation;

/**
 * Envoutement.java
 * Représente un effet déclenchable ou un bonus, temporaire, appliqué à une
 * entité active.
 *
 */
public abstract class Envoutement implements Declencheur {

	//Nom
	private final String nom;

	//Durée de l'envoutement en nombre de tours
	private int duree;

	private Entite cible;

	/**
	 *
	 * @param nom
	 * @param duree	nombre de tours
	 */
	public Envoutement(String nom, int duree) {
		this.nom = nom;
		this.duree = duree;
		cible = null;
	}

	public final void debutTour() {
		actionDebutTour();
	}

	public final void finTour() {
		actionFinTour();
	}

	public final void debutTourGlobal() {
		actionDebutTourGlobal();
	}

	public final void finTourGlobal() {
		actionFinTourGlobal();
	}

	public final void debutEnvoutement() {
		actionDebutEnvoutement();
	}

	public final void finEnvoutement() {
		actionFinEnvoutement();
	}

	/**
	 * Action lançée au début du tour de l'entité
	 */
	protected abstract void actionDebutTour();

	/**
	 * Action lançée à la fin du tour de l'entité
	 */
	protected abstract void actionFinTour();

	/**
	 * Action lançée au début du tour global
	 */
	protected abstract void actionDebutTourGlobal();

	/**
	 * Action lançée à la fin du tour global
	 */
	protected abstract void actionFinTourGlobal();

	/**
	 * Action lançée au moment du gain de l'envoutement
	 */
	protected abstract void actionDebutEnvoutement();

	/**
	 * Action lançée au moment de la perte de l'envoutement
	 * Cumulable avec actionFinTour()
	 */
	protected abstract void actionFinEnvoutement();

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (Declencheur declencheur : effet.getDeclencheur()) {
			if (declencheur.equals(this)) {
				Envoutement envoutement = (Envoutement) declencheur;
				if (envoutement.getDuree() <= min && envoutement.getDuree() >= max) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void lancerEntite(Entite victime, Orientation oriLanceur, boolean ccritique) {
		if (victime instanceof EntiteActive) {
			((EntiteActive) victime).addEnvoutement(this);
		}
		setCible(victime);
		actionDebutEnvoutement();
		actionLancerEntite(oriLanceur, ccritique);
	}

	protected abstract void actionLancerEntite(Orientation oriLanceur, boolean ccritique);

	public String getNom() {
		return nom;
	}

	public int getDuree() {
		return duree;
	}

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	/**
	 * equals en fonction du nom uniquement
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Envoutement other = (Envoutement) obj;
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		return true;
	}

	public void setCible(Entite cible) {
		this.cible = cible;
	}

	public Entite getCible() {
		return cible;
	}

	public boolean hasCible() {
		return cible != null;
	}

	public int subDuree() {
		duree--;
		return duree;
	}
}
