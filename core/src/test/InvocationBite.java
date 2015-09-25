package test;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.invocation.InvocationMobile;
import gameplay.map.Tuile;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import general.Orientation;
import java.awt.Point;

public class InvocationBite extends InvocationMobile {

	public InvocationBite() {
		super("une bite", 0, 0, Orientation.NORD, new SortPassif[]{},
				new SortActif[]{new SortQuiFaitMal(new Effet[]{})},
				new CaracteristiquePhysique(110, 12000, 1500, 0, 100),
				1, 1);
	}

	@Override
	public void invoquer(Point point) {
		System.out.println("Invocation suprème de la bite des enfert");

	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		return false;
	}

	@Override
	public void lancerEntite(Entite cible, Orientation oriLanceur, boolean ccritique) {
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
	}

}
