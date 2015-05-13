/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

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
	 * @param size
	 */
	public Personnalise(boolean posit, int size) {
		super(posit, size);
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
