/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

import gameplay.map.Map;
import gameplay.map.Tuile;
import java.awt.Point;

/**
 * Personnalise.java
 * Représente une zone dont la forme a été personnalisée par le joueur.
 * POUR LE MOMENT OSEF BITCH
 *
 */
public class Personnalise extends Zone {

	/**
	 *
	 * @param posit
	 */
	public Personnalise(boolean posit, int size, Map currentMap) {
		super(posit, size, currentMap);
	}

//	@Override
//	public Tuile[] getTilesOfInterrest(Point center) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}

	@Override
	public boolean[][] getZoneOfInterest() {
		throw new Error("TODO");
	}

}
