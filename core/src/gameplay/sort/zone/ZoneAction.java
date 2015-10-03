/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

import com.badlogic.gdx.utils.Array;

/**
 * ZoneAction.java
 * Gère l'ensemble des zones permettant l'affichage de la portée d'un sort
 * actif ou de sa zone d'action.
 *
 */
public class ZoneAction {

	//Liste des zones cumulables
	private final Array<Zone> listZones;

	//Zone intermédiaire sous forme de tableau 2D de booleen
	//false = vide
	private final boolean[][] zoneIntermediaire;

	//Taille de la moitié de la largeur de la zone
	private int size;

	/**
	 *
	 * @param zones
	 */
	public ZoneAction(Zone... zones) {
		listZones = new Array<>(zones);
		zoneIntermediaire = setZoneIntermediaire();
	}

	/**
	 * Calcul de la zone intermédiaire
	 *
	 * @return
	 */
	private boolean[][] setZoneIntermediaire() {
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

	public boolean[][] getZoneIntermediaire() {
		return zoneIntermediaire;
	}

}
