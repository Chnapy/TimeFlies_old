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

	//Niveau symbolique de l'entité
	private int niveau;

	/**
	 * Calcul le niveau d'après les sorts
	 *
	 * @param sorts
	 */
	public NiveauSymbolique(final Sort[] sorts) {
		calculNiveau(sorts);
	}

	/**
	 * Récupère le niveau symbolique
	 *
	 * @return niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * Ajoute le nombre au niveau simbolique
	 *
	 * utiliser pour l'ajout du ratio
	 *
	 * @param nombre
	 */
	public void add(int nombre) {
		this.niveau += nombre;
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
	private void calculNiveau(final Sort[] sorts) {
		if (sorts.length == 0) {
			return;
		}
		int[] tabNiveaux = new int[sorts.length];
		int[] min = new int[3], max = new int[3];
		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(max, 0);
		int i, j;

		for (i = 0; i < sorts.length; i++) {
			tabNiveaux[i] = sorts[i].getNiveau().getNiveauActu();
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

	/**
	 * Récupère la moyenne des valeurs du tableau
	 *
	 * @param tab
	 * @return
	 */
	private double getMoyenne(int[] tab) {
		int moyenne = 0;
		for (int t : tab) {
			moyenne += t;
		}
		return moyenne / tab.length;
	}

	@Override
	public String toString() {
		return "" + niveau;
	}

}
