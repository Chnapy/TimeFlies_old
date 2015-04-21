/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

/**
 * Envoutement.java
 * Représente un effet déclenchable ou un bonus, temporaire, appliqué à une
 * entité active.
 *
 */
public abstract class Envoutement {

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

}
