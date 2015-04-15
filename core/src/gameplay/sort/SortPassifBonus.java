/*
 * 
 * 
 * 
 */
package gameplay.sort;

import gameplay.caracteristique.Carac;
import com.badlogic.gdx.utils.Array;

/**
 * SortPassifBonus.java
 * Représente un sort passif donnant un bonus à son utilisateur, de manière
 * permannente.
 *
 */
public abstract class SortPassifBonus extends SortPassif {

	private Array<Carac> listCaracteristiques;
	private Array<Integer> listValeurs;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param caracteristiques
	 * @param valeurs
	 */
	public SortPassifBonus(String nom, String description, Niveau niveau,
			Array<Carac> caracteristiques,
			Array<Integer> valeurs) {

		super(nom, description, niveau);

		listCaracteristiques = caracteristiques;
		listValeurs = valeurs;
	}

}
