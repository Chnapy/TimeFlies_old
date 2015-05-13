/*
 * 
 * 
 * 
 */
package gameplay.sort;

/**
 * Niveau.java
 * Représente le niveau d'un sort, et gère son augmentation.
 *
 */
public class Niveau {

	/**
	 * Echelle d'augmentation des niveaux.
	 * Plus l'échelle est grande, plus il faudra d'xp pour monter de niveaux.
	 */
	private static final double ECHELLE = 0.1;

	//Niveau actuel
	private int niveauActu;

	//Expérience actuelle
	private int experienceActu;

	/**
	 * Niveau en fonction de l'experience.
	 *
	 * @param xp	experience
	 */
	public Niveau(int xp) {
		experienceActu = xp;
		niveauActu = calculNiveau();
	}

	/**
	 * Experience en fonction du niveau donné.
	 * Le paramêtre booleen 'rien' est inutile.
	 *
	 * @param niveau	niveau donné
	 * @param rien   osef
	 */
	public Niveau(int niveau, boolean rien) {
		experienceActu = calculExperience(niveau);
		niveauActu = niveau;

	}

	/**
	 * Gère le gain d'expérience et la montée de niveaux.
	 *
	 * @param xp	expérience gagnée
	 */
	public void gainExperience(int xp) {
		experienceActu += xp;
		int niveau = calculNiveau();
		if (niveau > niveauActu) {
			//UP !
			niveauActu = niveau;
		}
	}

	/**
	 * Calcul le niveau depuis l'expérience possédée.
	 * Utilise un algo logarithmique.
	 *
	 * niveau = racine(experience) * echelle
	 *
	 * @return	niveau
	 */
	private int calculNiveau() {
		return (int) (Math.sqrt(experienceActu) * ECHELLE);
	}

	/**
	 * Calcul l'expérience depuis un niveau.
	 * Utilise le même algo que pour calculNiveau() mais de manière inversée.
	 *
	 * experience = (niveau / echelle)²
	 *
	 * @param niveau	niveau
	 * @return	expérience totale du niveau
	 */
	public static int calculExperience(int niveau) {
		return (int) Math.pow(niveau / ECHELLE, 2);
	}

	public int getNiveauActu() {
		return niveauActu;
	}

	public int getExperienceActu() {
		return experienceActu;
	}

}
