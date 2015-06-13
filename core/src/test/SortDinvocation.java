package test;

import com.badlogic.gdx.utils.Array;
import gameplay.effet.Effet;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;

public class SortDinvocation extends SortActif {

	public SortDinvocation() {
		super("petite bite", "invoque une bite tellement minuscule qu'elle ferais presque la taille de celle de richard", new Niveau(1),
				new Effet[]{new Effet(Array.with(new InvocationBite()))},
				new ZoneAction(new Carre(2, true)), new ZoneAction(new Carre(1, true)), 2, 2000);
	}

}
