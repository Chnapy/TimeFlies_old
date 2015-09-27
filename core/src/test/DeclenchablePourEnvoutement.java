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
 * DeclenchablePourEnvoutement.java
 * 
 */
public class DeclenchablePourEnvoutement extends Declenchable {

	public DeclenchablePourEnvoutement() {
		super(new Balus(Carac.VITALITE, 25), -40, -20);
	}

}
