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
 * Zone.java
 * Représente une zone pouvant être positive ou négative.
 * Une zone positive représente une zone classique.
 * Une zone négative fait un "trou" dans une zone positive.
 *
 */
public abstract class Zone {

	private boolean positive;
        protected Map currentMap;
        protected int size;
        
	/**
	 *
	 * @param posit
	 */
	public Zone(boolean posit, int size, Map currentMap) {
		positive = posit;
                this.currentMap = currentMap;
                this.size = size;
	}
        
        /**
         * Make sure that a value is in bound.
         * @param value - Value.
         * @param max - Maximum value.
         * @return - In bound value.
         */
        protected int valueInBound(int value, int max){
        if(value > max)
            return max;
        else if(value < 0)
            return 0;
        else 
            return value;
    }
        
        public abstract Tuile[] getTilesOfInterrest(Point center);
        

}
