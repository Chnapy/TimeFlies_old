/*
 * 
 * 
 * 
 */
package test;

import gameplay.caracteristique.Carac;
import gameplay.effet.Balus;
import gameplay.effet.Declenchable;

/**
 * DeclenchablePourSortPassif.java
 * 
 */
public class DeclenchablePourSortPassif extends Declenchable {

	public DeclenchablePourSortPassif() {
		super(new Balus(Carac.VITALITE, -1), -999, -30);
	}

}
