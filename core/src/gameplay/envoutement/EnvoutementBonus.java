/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

/**
 * EnvoutementBonus.java
 * Représente un bonus, temporaire, appliqué à une entité active.
 *
 */
public abstract class EnvoutementBonus extends Envoutement {

	/**
	 *
	 * @param nom
	 * @param duree
	 */
	public EnvoutementBonus(String nom, int duree) {
		super(nom, duree);
	}

}
