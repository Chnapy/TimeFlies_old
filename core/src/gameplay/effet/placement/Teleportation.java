package gameplay.effet.placement;

import gameplay.caracteristique.CaracteristiqueSpatiale;
import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.map.Tuile;
import java.awt.Point;

/**
 * Effet de téléportation (effet de placement)
 * Téléporte le joueur au coordonée mises dans le constructeur
 *
 */
public class Teleportation extends Placement {

	//Position cible
	private Point position;

	public Teleportation(Point pos) {
		this.position = pos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Teleportation other = (Teleportation) obj;
		if (position == null) {
			if (other.getPosition() != null) {
				return false;
			}
		} else if (!position.equals(other.getPosition())) {
			return false;
		}
		return true;
	}

	public Point getPosition() {
		return position;
	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getDeclencheur().size; i++) {
			if (effet.getDeclencheur().get(i) instanceof Teleportation) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void lancerEntite(Entite victime, Orientation oriLanceur, boolean ccritique) {
		victime.setCaracSpatiale(new CaracteristiqueSpatiale((int) position.getX(), (int) position.getY(), victime.getCaracSpatiale().getOrientation()));
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
		lanceur.setPosition(cible.getPosition());
	}

}
