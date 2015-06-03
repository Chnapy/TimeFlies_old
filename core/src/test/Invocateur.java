package test;

import gameplay.caracteristique.CaracteristiquePhysique;
import gameplay.caracteristique.Orientation;
import gameplay.entite.Personnage;
import gameplay.sort.SortActif;
import gameplay.sort.SortPassif;

/**
 * un invocateur
 * @author ydardot
 *
 */
public class Invocateur extends Personnage {

	private static final int INDEX_TEXTURE = 1;
	private static final int INDEX_TEXTURE_TIMELINE = 1;
	
	/**
	 * 
	 * @param nomDonne
	 * @param posX
	 * @param posY
	 * @param orientation
	 * @param cPhysique
	 * @param sortsPassifs
	 * @param sortsActifs
	 */
	public Invocateur(String nomDonne, int posX, int posY,
			Orientation orientation) {
		super("Invocatueur", nomDonne, posX, posY, orientation,
				new CaracteristiquePhysique(100, 100, 100, 100, 10, 10, 0, 0, 1, 1), 
				new SortPassif[]{},
				new SortActif[]{new SortDinvocation()}, INDEX_TEXTURE, INDEX_TEXTURE_TIMELINE);
	}

}