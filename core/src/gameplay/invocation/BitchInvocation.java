package gameplay.invocation;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.map.Tuile;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import java.awt.Point;

public class BitchInvocation extends InvocationMobile {

	public BitchInvocation() {
		super("Bitchator", 0, 0, Orientation.N, new SortPassif[]{}, new SortActif[]{},
				new CaracteristiquePhysique(100, 100, 10, 10, 0, 0, 0, 0, 0, 0),
				0, 0);
	}

	@Override
	public void invoquer(Point point) {

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
