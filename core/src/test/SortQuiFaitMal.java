/*
 * 
 * 
 * 
 */
package test;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Carac;
import gameplay.effet.Balus;
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

	private final static String NOM = "SortQuiFaitMal";
	private final static String DESCRIPTION = "Ca fait mal";
	private final static int INDEX = 1;

	public SortQuiFaitMal() {
		super(
				NOM,
				DESCRIPTION,
				new Niveau(0),
				new Effet[]{
					new Effet(new Declencheur[]{
						new Balus(Carac.VITALITE, -30)
					})
				},
				new ZoneAction(new Carre(2, true), new Carre(1, false)),
				new ZoneAction(new Carre(1, true)),
				INDEX,
				1000,
				0,
				2);
	}

}
