package gameplay.invocation;

import com.badlogic.gdx.math.GridPoint2;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.map.Tuile;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;
import general.Orientation;
import static general.Orientation.NORD;

public class BitchInvocation extends InvocationMobile {

	public BitchInvocation() {
		super("Bitchator", 0, 0, NORD, new SortPassif[]{}, new SortActif[]{},
				new CaracteristiquePhysique(110, 12000, 1500, 0, 100),
				0, 0);
	}

	@Override
	public void invoquer(GridPoint2 point) {

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
