package test;

import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;

public class SortInvocationPassive extends SortActif {

	public SortInvocationPassive() {
		super(
				"Sort d'invocation",
				"Sort d'invocation de test qui se soigne lors de la reception de degats (wahou...)",
				new Niveau(1),
				new Effet[]{
					new Effet(new Declencheur[]{
						new InvocationPassiveTest()
					})
				},
				new ZoneAction(new Carre(2, true)),
				new ZoneAction(new Carre(0, true)),
				4,
				2000,
				4
		);
	}

}
