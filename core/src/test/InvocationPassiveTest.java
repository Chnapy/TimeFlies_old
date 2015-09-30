package test;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.invocation.InvocationPassive;
import gameplay.map.Tuile;
import gameplay.sort.SortPassif;
import general.Orientation;
import java.awt.Point;

public class InvocationPassiveTest extends InvocationPassive {

	public InvocationPassiveTest() {
		super("Invoc. passive",
				0,
				0,
				Orientation.NORD,
				new SortPassif[]{
					new SortPassifEffetSoin()
				},
				new CaracteristiquePhysique(110, 0, 1500, 0, 100),
				1);
	}

	@Override
	public void invoquer(Point point) {
		getCaracSpatiale().getPosition().setLocation(point);
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

	@Override
	public void jouerTour(long time) {
	}

}
