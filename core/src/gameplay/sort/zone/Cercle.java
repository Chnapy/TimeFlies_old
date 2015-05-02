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
import javafx.scene.shape.Circle;

/**
 * Cercle.java
 * Représente une zone en forme de cercle.
 *
 */
public class Cercle extends Zone {

	/**
	 *
	 * @param rayon	rayon du cercle
	 * @param posit	positif ?
	 */
	public Cercle(int radius, boolean posit, Map currentMap) {
		super(posit, radius, currentMap);
	}

//	@Override
//	public Tuile[] getTilesOfInterrest(Point center) {
//		ArrayList<Tuile> tiles = new ArrayList<Tuile>();
//		Tuile[][] mapTiles = currentMap.getTabTuiles();
//
//		Circle hitBox = new Circle(center.x, center.y, size);
//
//		for (int x = 0; x < mapTiles.length; x++) {
//			for (int y = 0; y < mapTiles[x].length; y++) {
//				if (hitBox.intersects(x, y, 1, 1)) {
//					tiles.add(mapTiles[x][y]);
//				}
//			}
//		}
//
//		Tuile[] result = new Tuile[tiles.size()];
//		return tiles.toArray(result);
//	}

	@Override
	public boolean[][] getZoneOfInterest() {
		throw new Error("TODO");
	}

}
