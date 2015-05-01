/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

import com.badlogic.gdx.utils.Array;
import gameplay.map.Tuile;

/**
 * ZonePortee.java
 * Gère l'ensemble des zones permettant l'affichage de la portée d'un sort
 * actif.
 *
 */
public class ZonePortee {

	private final Array<Zone> listZones;
	private final Tuile[] tabTuiles;

	/**
	 *
	 * @param zones
	 */
	public ZonePortee(Zone... zones) {
		listZones = new Array<>(zones);
		tabTuiles = setTuiles();
	}

	//TODO
	private Tuile[] setTuiles() {
		Array<Tuile> tuiles = new Array<>();
		listZones.sort();
		listZones.forEach((zone) -> {
			if (zone.isPositive()) {

			} else {

			}
		});

		return null;
	}

	public Array<Zone> getListZones() {
		return listZones;
	}

	public Tuile[] getTabTuiles() {
		return tabTuiles;
	}

}
