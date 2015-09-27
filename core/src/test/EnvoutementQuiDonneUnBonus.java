/*
 * 
 * 
 * 
 */
package test;

import gameplay.caracteristique.Carac;
import gameplay.envoutement.EnvoutementBonus;

/**
 * EnvoutementQuiDonneUnBonus.java
 *
 */
public class EnvoutementQuiDonneUnBonus extends EnvoutementBonus {

	private static final Carac CARAC = Carac.VITALITE;
	private static final int VALEUR = 40;

	public EnvoutementQuiDonneUnBonus() {
		super("EnvoutementQuiDonneUnBonus", 1, CARAC, VALEUR);
	}

}
