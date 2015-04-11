/*
 * 
 * 
 * 
 */
package gameplay.entite;

import com.badlogic.gdx.utils.Array;
import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.envoutement.Envoutement;

/**
 * EntiteActive.java
 * Représente une entité active (controlable par un joueur).
 *
 */
public abstract class EntiteActive extends Entite {

	private final CaracteristiquePhysique caracPhysique;

	private Array<Envoutement> listEnvoutements;

	public EntiteActive(String nom,
			int posX, int posY, Orientation orientation,
			CaracteristiquePhysique cPhysique) {

		super(nom, posX, posY, orientation);

		caracPhysique = cPhysique;
		listEnvoutements = new Array<Envoutement>();
	}

}
