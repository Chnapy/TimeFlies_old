/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

import com.badlogic.gdx.utils.Array;

/**
 * ZoneAction.java
 * Gère l'ensemble des zones permettant l'exécution d'un sort actif.
 *
 */
public class ZoneAction {

	private final Array<Zone> listZones;

	/**
	 *
	 * @param zones
	 */
	public ZoneAction(Zone... zones) {
		listZones = new Array<>(zones);
	}

}
