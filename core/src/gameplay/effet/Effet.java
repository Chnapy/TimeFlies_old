/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;
import gameplay.envoutement.Envoutement;
import gameplay.invocation.Invocation;

/**
 * Effet.java
 * Représente un effet.
 * Peut être (un ou plusieurs) :
 * - un ou des bonus/malus de caractéristiques physiques
 * - un ou des envoutements
 * - un placement
 * - une invocation
 *
 */
public class Effet {

	private Array<Balus> listBalus;
	private Array<Envoutement> listEnvoutements;
	private Placement placement;
	private Invocation invocation;

	public Effet(Array<Balus> balus, Array<Envoutement> envoutements, Placement place, Invocation invoc) {
		listBalus = balus;
		listEnvoutements = envoutements;
		placement = place;
		invocation = invoc;
	}

}
