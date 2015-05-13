/*
 * 
 * 
 * 
 */
package gameplay.sort;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.Carac;
import gameplay.effet.Effet;

/**
 * SortPassifBonus.java
 * Représente un sort passif donnant un bonus à son utilisateur, de manière
 * permannente.
 *
 */
public abstract class SortPassifBonus extends SortPassif {

	//Liste des caractéristiques donnant un bonus
	private Array<Carac> listCaracteristiques;

	//liste des valeur du bonus de chaque caractéristiques
	private Array<Integer> listValeurs;

	/**
	 *
	 * @param nom
	 * @param description
	 * @param niveau
	 * @param effets
	 * @param caracteristiques
	 * @param valeurs
	 * @param index
	 */
	public SortPassifBonus(String nom, String description, Niveau niveau,
			Effet[] effets,
			Array<Carac> caracteristiques,
			Array<Integer> valeurs,
			int index) {

		super(nom, description, niveau, effets, index);

		listCaracteristiques = caracteristiques;
		listValeurs = valeurs;
	}

}
