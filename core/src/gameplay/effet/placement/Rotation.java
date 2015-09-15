/*
 * 
 * 
 * 
 */
package gameplay.effet.placement;

import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.map.Tuile;
import general.Orientation;

/**
 * Rotation.java
 *
 */
public class Rotation extends Placement {

	public Rotation() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		return getClass() == obj.getClass();
	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
//		System.out.println("declench");
		for (int i = 0; i < effet.getDeclencheur().size; i++) {
			if (effet.getDeclencheur().get(i) instanceof Rotation) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void lancerEntite(Entite victime, Orientation oriLanceur, boolean ccritique) {
//		System.out.println(victime.getNom());
		victime.getCaracSpatiale().setOrientation(oriLanceur);
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
//		System.out.println("tuile");
	}

}
