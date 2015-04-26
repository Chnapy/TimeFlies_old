/*
 * 
 * 
 * 
 */
package test;

import gameplay.effet.Effet;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.ZoneAction;
import gameplay.sort.zone.ZonePortee;

/**
 * SortQuiFaitMal.java
 *
 */
public class SortQuiFaitMal extends SortActif {

	public SortQuiFaitMal() {
		super("SortQuiFaitMal", "Ca fait mal", new Niveau(0), new Effet[]{}, new ZonePortee(), new ZoneAction(), 0);
	}

}
