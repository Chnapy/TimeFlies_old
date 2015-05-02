/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

import gameplay.map.Map;
import gameplay.map.Tuile;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Carre.java
 * Représente une zone en forme de carré.
 *
 */
public class Carre extends Zone {

	/**
	 *
	 * @param cote	 longueur d'un coté
	 * @param posit	positif ?
	 */
	public Carre(int cote, boolean posit, Map currentMap) {
		super(posit, cote, currentMap);
	}

	//Not tested yet.
//	@Override
//	public Tuile[] getTilesOfInterrest(Point center) {
//		ArrayList<Tuile> tiles = new ArrayList<Tuile>();
//		Tuile[][] mapTiles = currentMap.getTabTuiles();
//
//		int maxX = currentMap.getMapDimension().width;
//		int maxY = currentMap.getMapDimension().height;
//
//		Point start = new Point(super.valueInBound(center.x - size, maxX), super.valueInBound(center.y - size, maxY));
//		Point stop = new Point(super.valueInBound(center.x + size, maxX), super.valueInBound(center.y + size, maxY));
//
//		for (int x = start.x; x <= stop.x; x++) {
//			for (int y = start.y; y <= stop.y; y++) {
//				tiles.add(mapTiles[x][y]);
//			}
//		}
//
//		Tuile[] result = new Tuile[tiles.size()];
//		return tiles.toArray(result);
//	}

	@Override
	public boolean[][] getZoneOfInterest() {
		boolean[][] mapPositions = new boolean[size][size];
		for (boolean[] mp : mapPositions) {
			Arrays.fill(mp, true);
		}
		mapPositions[size / 2][size / 2] = false;

		return mapPositions;
	}

}
