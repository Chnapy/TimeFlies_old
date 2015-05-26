package gameplay.invocation;

import java.awt.Point;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

public class BitchInvocation extends InvocationMobile {

	public BitchInvocation() {
		super("Bitchator", 0, 0, Orientation.N, new SortPassif[] {}, new SortActif[] {}, new CaracteristiquePhysique(100, 100, 10, 10, 0, 0, 0, 0, 0, 0),
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
	public void lancer(Entite cible, int pourcentageSupp) {
				
	}

}
