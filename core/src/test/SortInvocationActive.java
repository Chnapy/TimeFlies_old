package test;

import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;

public class SortInvocationActive extends SortActif {

	public SortInvocationActive() {
		super(
				"Sort d'invocation",
				"Sort d'invocation de test",
				new Niveau(1),
				new Effet[]{
					new Effet(new Declencheur[]{
						new InvocationMobileTest()
					})
				},
				new ZoneAction(new Carre(1, true)),
				new ZoneAction(new Carre(0, true)),
				5,
				2000,
				5,
				20
		);
	}

}
