/*
 * 
 * 
 * 
 */
package test;

import gameplay.effet.Effet;
import gameplay.map.Map;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;
import gameplay.sort.zone.ZonePortee;

/**
 * SortQuiFaitMal.java
 *
 */
public class SortQuiFaitMal extends SortActif {

	public SortQuiFaitMal(Map map) {
		super("SortQuiFaitMal", "Ca fait mal", new Niveau(0),
				new Effet[]{},
				new ZonePortee(new Carre(5, true, map), new Carre(3, false, map)),
				new ZoneAction(),
				0);
	}

}
