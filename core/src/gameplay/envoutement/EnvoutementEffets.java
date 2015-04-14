/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

import com.badlogic.gdx.utils.Array;

/**
 * EnvoutementEffets.java
 * Représente un effet déclenchable, temporaire, appliqué à une entité active.
 *
 */
public abstract class EnvoutementEffets extends Envoutement {

	private Array<Declenchable> listDeclenchables;

	/**
	 *
	 * @param nom
	 * @param duree
	 * @param declenchables
	 */
	public EnvoutementEffets(String nom, int duree,
			Array<Declenchable> declenchables) {
		super(nom, duree);
		listDeclenchables = declenchables;
	}

}
