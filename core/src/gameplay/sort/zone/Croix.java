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

/**
 * Croix.java
 * Représente une zone en forme de croix.
 *
 */
public class Croix extends Zone {

	/**
	 *
	 * @param taille	taille d'une partie de la croix
	 * @param positive	positif ?
	 */
	public Croix(int size, boolean positive, Map currentMap) {
		super(positive, size, currentMap);
		//TODO taille
	}

    @Override
    public Tuile[] getTilesOfInterrest(Point center) {
        ArrayList<Tuile> tiles = new ArrayList<Tuile>();
        Tuile[][] mapTiles = currentMap.getTabTuiles();
        
        int maxX = currentMap.getMapDimension().width;
        int maxY = currentMap.getMapDimension().height;
        
        Point start = new Point(super.valueInBound(center.x - size, maxX), super.valueInBound(center.y - size, maxY));
        Point stop = new Point(super.valueInBound(center.x + size, maxX), super.valueInBound(center.y + size, maxY));
        
        for (int x = start.x; x < stop.x; x++) 
            tiles.add(mapTiles[x][center.y]);
            
        for (int y = start.y; y < stop.y; y++) 
            tiles.add(mapTiles[center.x][y]);
        
        
        Tuile[] result = new Tuile[tiles.size()];
        return tiles.toArray(result);
    }

}
