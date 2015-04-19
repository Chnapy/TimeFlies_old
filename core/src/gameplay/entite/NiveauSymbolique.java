/*
 * 
 * 
 * 
 */
package gameplay.entite;

import gameplay.sort.Sort;
import java.util.Arrays;

/**
 * NiveauSymbolique.java
 * Gère le niveau symbolique du personnage d'après :
 * - le niveau de ses sorts
 * - le ratio victoires/défaites du joueur
 * Il est redéfini à chaque tour global.
 *
 */
public class NiveauSymbolique {

	private int niveau;

	/**
	 *
	 * @param sortsPassifs
	 * @param sortsActifs
	 */
	public NiveauSymbolique(final Sort[] sortsPassifs, final Sort[] sortsActifs) {
		calculNiveau(sortsPassifs, sortsActifs);
	}

	/**
	 * Calcul du niveau symbolique :
	 * Récupération du niveau de chacun des sorts.
	 * Le niveau est calculé de cette manière :
	 * 15% : Moyenne des 3 niveaux de sort les plus faibles
	 * 25% : Moyenne des 3 niveaux de sort les plus forts
	 * 60% : Moyenne de l'ensemble des niveaux de sort
	 *
	 * TODO : Prendre en compte le ratio victoires/défaites
	 *
	 */
	private void calculNiveau(final Sort[] sortsPassifs, final Sort[] sortsActifs) {
		if (sortsPassifs.length + sortsActifs.length == 0) {
			return;
		}
		int[] tabNiveaux = new int[sortsPassifs.length + sortsActifs.length];
		int[] min = new int[3], max = new int[3];
		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(max, 0);
		int i, j;

		for (i = 0; i < sortsPassifs.length; i++) {
			tabNiveaux[i] = sortsPassifs[i].getNiveau().getNiveauActu();
			for (j = 0; j < min.length; j++) {
				if (min[j] > tabNiveaux[j]) {
					min[j] = tabNiveaux[j];
					break;
				}
			}
			for (j = 0; j < max.length; j++) {
				if (max[j] < tabNiveaux[j]) {
					max[j] = tabNiveaux[j];
					break;
				}
			}
		}
		for (i = sortsPassifs.length; i < sortsActifs.length; i++) {
			tabNiveaux[i] = sortsActifs[i].getNiveau().getNiveauActu();
			for (j = 0; j < min.length; j++) {
				if (min[j] > tabNiveaux[i]) {
					min[j] = tabNiveaux[i];
					break;
				}
			}
			for (j = 0; j < max.length; j++) {
				if (max[j] < tabNiveaux[i]) {
					max[j] = tabNiveaux[i];
					break;
				}
			}
		}

		niveau = (int) (getMoyenne(min) * 0.15 + getMoyenne(max) * 0.25 + getMoyenne(tabNiveaux) * 0.60);

	}

	private double getMoyenne(int[] tab) {
		int moyenne = 0;
		for (int t : tab) {
			moyenne += t;
		}
		return moyenne / tab.length;
	}

}
