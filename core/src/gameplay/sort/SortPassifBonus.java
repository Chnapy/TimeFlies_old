/*
 * 
 * 
 * 
 */
package gameplay.sort;

import com.badlogic.gdx.utils.Array;

/**
 * SortPassifBonus.java
 * Représente un sort passif donnant un bonus à son utilisateur, de manière
 * permannente.
 *
 */
public class SortPassifBonus extends SortPassif {

	private Array<Caracteristique> listCaracteristiques;
	private Array<Integer> listValeurs;

	public SortPassifBonus(String nom, String description, Niveau niveau,
			Array<Caracteristique> caracteristiques,
			Array<Integer> valeurs) {

		super(nom, description, niveau);

		listCaracteristiques = caracteristiques;
		listValeurs = valeurs;
	}

}
