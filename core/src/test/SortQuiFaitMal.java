/*
 * 
 * 
 * 
 */
package test;

import com.badlogic.gdx.utils.Array;

import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;

/**
 * SortQuiFaitMal.java
 *
 */
public class SortQuiFaitMal extends SortActif {

	public SortQuiFaitMal(Effet[] effets) {
		super("SortQuiFaitMal", "Ca fait mal", new Niveau(0),
				effets,
				new ZoneAction(new Carre(2, true), new Carre(1, false)),
				new ZoneAction(new Carre(2, true), new Carre(1, true)),
				0,5000);
	}

}
