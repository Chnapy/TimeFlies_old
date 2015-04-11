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
	private int duree;

	public Envoutement(String nom, int duree) {
		this.nom = nom;
		this.duree = duree;
	}

}
