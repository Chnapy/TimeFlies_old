/*
 * 
 * 
 * 
 */
package gameplay.sort.zone;

/**
 * Diagonales.java
 * Représente une zone en forme de 2 diagonales qui se coupent en leur centre.
 *
 */
public class Diagonales extends Zone {

	/**
	 *
	 * @param taille	taille de chaque diagonale
	 * @param posit
	 */
	public Diagonales(int size, boolean posit) {
		super(posit, size);
		//TODO taille
	}

	// TODO 
//	public Tuile[] getTilesOfInterrest(Point center) {
//
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}

	@Override
	public boolean[][] getZoneOfInterest() {
		throw new Error("TODO");
	}

}
