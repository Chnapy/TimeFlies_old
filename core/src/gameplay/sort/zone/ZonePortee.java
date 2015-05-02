/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

import com.badlogic.gdx.utils.Array;
import java.util.Arrays;

/**
 * ZonePortee.java
 * Gère l'ensemble des zones permettant l'affichage de la portée d'un sort
 * actif.
 *
 */
public class ZonePortee {

	private final Array<Zone> listZones;

	//false = vide
	private final boolean[][] zoneFinale;
	private int size;

	/**
	 *
	 * @param zones
	 */
	public ZonePortee(Zone... zones) {
		listZones = new Array<>(zones);
		zoneFinale = setZoneFinale();

		//DEBUG
//		zoneFinale = new boolean[][]{
//			{false, true, true, true, false},
//			{true, false, true, false, true},
//			{true, true, false, true, true},
//			{true, false, true, false, true},
//			{false, true, true, true, false}
//		};
	}

	//TODO
	private boolean[][] setZoneFinale() {
		size = 0;

		//Tri des zones, avec les positives en 1er
		listZones.sort();

		//Récupération de la taille de zone max
		listZones.forEach((zone) -> {
			if (zone.size > size) {
				size = zone.size;
			}
		});
		boolean[][] tab = new boolean[size][size];

		//Création de la zone finale
		listZones.forEach((zone) -> {
			boolean[][] zoneTab = zone.getZoneOfInterest();
			if (zone.isPositive()) {
				for (int y = (size - zone.size) / 2, j = 0; y < size - (size - zone.size) / 2; y++, j++) {
					for (int x = (size - zone.size) / 2, i = 0; x < size - (size - zone.size) / 2; x++, i++) {
						if (zoneTab[j][i]) {
							tab[y][x] = zoneTab[j][i];
						}
					}
				}
			} else {
				for (int y = (size - zone.size) / 2, j = 0; y < size - (size - zone.size) / 2; y++, j++) {
					for (int x = (size - zone.size) / 2, i = 0; x < size - (size - zone.size) / 2; x++, i++) {
						if (zoneTab[j][i]) {
							tab[y][x] = !zoneTab[j][i];
						}
					}
				}
			}
		});

		return tab;
	}

	public Array<Zone> getListZones() {
		return listZones;
	}

	public boolean[][] getZoneFinale() {
		return zoneFinale;
	}

}
