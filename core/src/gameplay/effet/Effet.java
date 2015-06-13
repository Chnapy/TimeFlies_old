/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Orientation;
import gameplay.entite.Entite;
import gameplay.map.Tuile;

/**
 * Effet.java
 * Représente une liste de déclencheur pouvant lancerEntite un effet.
 * Peut posséder (un ou plusieurs) :
 * - un ou des bonus/malus de caractéristiques physiques
 * - un ou des envoutements
 * - un placement
 * - une invocation
 *
 */
public class Effet {

	//Liste des déclencheurs
	private Array<Declencheur> declencheur;

	/**
	 *
	 * @param declencheur
	 */
	public Effet(Array<Declencheur> declencheur) {
		this.declencheur = declencheur;
	}

	/**
	 * Lance les effets sur la cible sans prendre en compte les passifs
	 *
	 * @param cible
	 * @param oriLanceur
	 * @param ccritique
	 */
	public void lancerEffetEntite(Entite cible, Orientation oriLanceur, boolean ccritique) {
		if (declencheur != null && declencheur.size != 0) {
			for (int i = 0; i < declencheur.size; i++) {
				this.declencheur.get(i).lancerEntite(cible, oriLanceur, ccritique);
			}
		}
	}

	public void lancerEffetTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
		if (declencheur != null && declencheur.size != 0) {
			for (int i = 0; i < declencheur.size; i++) {
				this.declencheur.get(i).lancerTuile(cible, lanceur, oriLanceur, ccritique);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((declencheur == null) ? 0 : declencheur.hashCode());
		return result;
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
		Effet other = (Effet) obj;
		if (declencheur == null) {
			if (other.declencheur != null) {
				return false;
			}
		} else if (!declencheur.equals(other.declencheur)) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * @return tout les déclancheur
	 */
	public Array<Declencheur> getDeclencheur() {
		return declencheur;
	}

	/**
	 * met a jours les déclancheur
	 *
	 * @param declencheur
	 */
	public void setDeclencheur(Array<Declencheur> declencheur) {
		this.declencheur = declencheur;
	}

}
