package gameplay.effet;

import gameplay.caracteristique.Orientation;
import gameplay.entite.Entite;

/**
 *
 * @author ydardot
 * permet d'atirer/pousser un joueur
 */
public class Deplacement extends Placement {

	private int nombre;
	private Orientation direction;

	public int getNombre() {
		return nombre;
	}

	public Orientation getDirection() {
		return direction;
	}

	public Deplacement(int nombre, Orientation direction) {
		super();
		this.nombre = nombre;
		this.direction = direction;
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
		Deplacement other = (Deplacement) obj;
		if (direction != other.direction) {
			return false;
		}
		if (nombre != other.nombre) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canDeclencher(Effet effet, int min, int max) {
		for (int i = 0; i < effet.getDeclencheur().size; i++) {
			if (effet.getDeclencheur().get(i) instanceof Deplacement && ((Deplacement) effet.getDeclencheur().get(i)).nombre <= min && ((Deplacement) effet.getDeclencheur().get(i)).nombre >= max) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void lancer(Entite victime, int pourcentage) {
		if (direction.equals(Orientation.E)) {
			victime.getCaracSpatiale().move(nombre, 0);
		} else if (direction.equals(Orientation.N)) {
			victime.getCaracSpatiale().move(0, -nombre);
		} else if (direction.equals(Orientation.O)) {
			victime.getCaracSpatiale().move(-nombre, 0);
		} else if (direction.equals(Orientation.S)) {
			victime.getCaracSpatiale().move(0, nombre);
		}
	}

}
