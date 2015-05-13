/*
 * 
 * 
 * 
 */
package gameplay.effet;

import com.badlogic.gdx.utils.Array;
import gameplay.entite.Entite;

/**
 * Effet.java
 * Représente une liste de déclencheur pouvant lancer un effet.
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
	 */
	public Effet(Array<Declencheur> declencheur) {
		this.declencheur = declencheur;
	}

	/**
	 * Lance les effets sur la cible sans prendre en compte les passifs
	 *
	 * @param cible
	 * @param pourcentageSupp
	 */
	public void lancerEffet(Entite cible, int pourcentageSupp) {
		if (declencheur != null && declencheur.size != 0) {
			for (int i = 0; i < declencheur.size; i++) {
				this.declencheur.get(i).lancer(cible, pourcentageSupp);
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
