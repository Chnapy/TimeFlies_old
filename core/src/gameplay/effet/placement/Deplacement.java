package gameplay.effet.placement;

import gameplay.caracteristique.Orientation;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.map.Tuile;

/**
 * Permet d'atirer/pousser un joueur
 *
 */
public class Deplacement extends Placement {

	//Nombre de tuiles déplacées
	private int nombre;

	public Deplacement(int nombre) {
		this.nombre = nombre;
	}

	public int getNombre() {
		return nombre;
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
		return nombre == other.nombre;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + this.nombre;
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
	public void lancerEntite(Entite victime, Orientation oriLanceur, boolean ccritique) {
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
		System.out.println(lanceur.getCaracSpatiale().getPosition());
		switch (oriLanceur) {
			case E:
				lanceur.move(nombre, 0);
				break;
			case N:
				lanceur.move(0, -nombre);
				break;
			case O:
				lanceur.move(-nombre, 0);
				break;
			case S:
				lanceur.move(0, nombre);
				break;
			default:
				throw new Error("direction non gérée.");
		}
	}

}
