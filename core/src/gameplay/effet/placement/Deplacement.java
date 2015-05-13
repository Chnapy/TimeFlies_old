package gameplay.effet.placement;

import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import java.util.Objects;

/**
 * Permet d'atirer/pousser un joueur
 *
 */
public class Deplacement extends Placement {

	//Nombre de tuiles déplacées
	private int nombre;

	//Direction dans laquelle le deplacement se fait
	private Orientation direction;

	public Deplacement(int nombre, Orientation direction) {
		this.nombre = nombre;
		this.direction = direction;
	}

	public int getNombre() {
		return nombre;
	}

	public Orientation getDirection() {
		return direction;
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
		return nombre == other.nombre;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + this.nombre;
		hash = 23 * hash + Objects.hashCode(this.direction);
		return hash;
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
		switch (direction) {
			case E:
				victime.getCaracSpatiale().move(nombre, 0);
				break;
			case N:
				victime.getCaracSpatiale().move(0, -nombre);
				break;
			case O:
				victime.getCaracSpatiale().move(-nombre, 0);
				break;
			case S:
				victime.getCaracSpatiale().move(0, nombre);
				break;
			default:
				throw new Error("direction non gérée.");
		}
	}

}
