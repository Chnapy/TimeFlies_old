/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

import gameplay.effet.Declencheur;
import gameplay.effet.Effet;

/**
 * Envoutement.java
 * Représente un effet déclenchable ou un bonus, temporaire, appliqué à une
 * entité active.
 *
 */
public abstract class Envoutement implements Declencheur {

	public String getNom() {
		return nom;
	}

	public int getDuree() {
		return duree;
	}

	private String nom;
	private int duree;	//En nombre de tours

	/**
	 *
	 * @param nom
	 * @param duree	nombre de tours
	 */
	public Envoutement(String nom, int duree) {
		this.nom = nom;
		this.duree = duree;
	}

	/**
	 * Action lançée au début du tour de l'entité
	 */
	public abstract void actionDebutTour();

	/**
	 * Action lançée à la fin du tour de l'entité
	 */
	public abstract void actionFinTour();

	/**
	 * Action lançée au début du tour global
	 */
	public abstract void actionDebutTourGlobal();

	/**
	 * Action lançée à la fin du tour global
	 */
	public abstract void actionFinTourGlobal();

	/**
	 * Action lançée au moment du gain de l'envoutement
	 */
	public abstract void actionDebutEnvoutement();

	/**
	 * Action lançée au moment de la perte de l'envoutement
	 * Cumulable avec actionFinTour()
	 */
	public abstract void actionFinEnvoutement();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

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

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getListEnvoutements().size; i++) {
			if (this.equals(effet.getListEnvoutements().get(i))) {
				if (effet.getListEnvoutements().get(i).getDuree() >= min && effet.getListEnvoutements().get(i).getDuree() <= max) {
					return true;
				}
			}
		}
		return false;
	}
}
