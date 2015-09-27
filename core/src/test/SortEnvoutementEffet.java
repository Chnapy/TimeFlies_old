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
 * SortEnvoutementEffet.java
 *
 */
public class SortEnvoutementEffet extends SortActif {

	public SortEnvoutementEffet() {
		super("SortEnvoutementEffet",
				"Un sort qui donne un envoutement effet",
				new Niveau(0),
				new Effet[]{
					new Effet(new Declencheur[]{
						new EnvoutementQuiSeDeclenche()
					})
				},
				new ZoneAction(new Carre(2, true)),
				new ZoneAction(new Carre(0, true)),
				3,
				1500,
				1);
	}

}
