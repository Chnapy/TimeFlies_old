/*
 * 
 * 
 * 
 */
package gameplay.envoutement;

import com.badlogic.gdx.utils.Array;

import gameplay.effet.Declenchable;
import gameplay.effet.Effet;

/**
 * EnvoutementEffets.java
 * Représente un effet déclenchable, temporaire, appliqué à une entité active.
 * Les effets de l'envoutement s'appliquent lorsque l'ensemble des déclenchables
 * sont activés.
 *
 */
public abstract class EnvoutementEffets extends Envoutement {

	private Array<Declenchable> listDeclenchables;
	private Array<Effet> listEffets;

	/**
	 *
	 * @param nom
	 * @param duree
	 * @param declenchables
	 */
	public EnvoutementEffets(String nom, int duree,
			Array<Declenchable> declenchables,
			Array<Effet> effets) {
		super(nom, duree);
		listDeclenchables = declenchables;
		listEffets = effets;
	}
	
	
}
