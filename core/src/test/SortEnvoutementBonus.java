/*
 * 
 * 
 * 
 */
package test;

import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;

/**
 * SortEnvoutementBonus.java
 * 
 */
public class SortEnvoutementBonus extends SortActif {

	public SortEnvoutementBonus() {
		super("SortEnvoutementEffet", 
				"Un sort qui donne un envoutement bonus", 
				new Niveau(0), 
				new Effet[] {
					new Effet(new Declencheur[]{
						new EnvoutementQuiDonneUnBonus()
					})
				}, 
				new ZoneAction(new Carre(2, true)), 
				new ZoneAction(new Carre(1, true)), 
				2, 
				1500,
				1);
	}

}
