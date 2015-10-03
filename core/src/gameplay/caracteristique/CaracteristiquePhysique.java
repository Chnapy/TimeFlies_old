/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

import java.util.Arrays;

/**
 * CaracteristiquePhysique.java
 * Gère l'ensemble des caractéristiques physiques de l'entité.
 *
 */
public class CaracteristiquePhysique {

	//Tableau des caractéristiques possédées
	private Caracteristique[] listCaracteristiques;

	/**
	 * Représente l'ensemble des caractéristiques possédées : vitalité, temps
	 * d'action, temps supplémentaire, fatigue, vitesse d'action
	 *
	 * @param vitaTotal
	 * @param tActionTotal
	 * @param tSupTotal
	 * @param fatActu
	 * @param vActionActu
	 */
	public CaracteristiquePhysique(int vitaTotal, int tActionTotal, int tSupTotal, int fatActu, int vActionActu) {

		listCaracteristiques = new Caracteristique[]{
			new Vitalite(vitaTotal),
			new TempsAction(tActionTotal),
			new TempsSup(tSupTotal),
			new Fatigue(fatActu),
			new VitesseAction(vActionActu),
			new Initiative(-1)
		};

	}

	/**
	 * Ajoute une valeur à la valeur actu de la caractéristique donnée
	 *
	 * @param c
	 * @param gain
	 */
	public void add(Carac c, int gain) {
		get(c).add(gain);
		majTempsActionParFatigue(c);
	}

	/**
	 * Redéfini le temps d'action d'après la fatigue
	 *
	 * @param c
	 */
	private void majTempsActionParFatigue(Carac c) {
		if (c.equals(Carac.FATIGUE)) {
			((TempsAction) get(Carac.TEMPSACTION)).setTotal(
					((TempsAction) get(Carac.TEMPSACTION)).getTempsBase()
					- ((((TempsAction) get(Carac.TEMPSACTION)).getTempsBase() * get(c).getActu()) / 100)
			);
		}
	}

	/**
	 * Enleve une valeur à la valeur actu de la caractéristique donnée
	 *
	 * @param c
	 * @param perte
	 */
	public void supp(Carac c, int perte) {
		get(c).supp(perte);
		majTempsActionParFatigue(c);
	}

	/**
	 * Défini la valeur actu de la caractéristique donnée
	 *
	 * @param c
	 * @param valeur
	 */
	public void setActu(Carac c, int valeur) {
		get(c).setActu(valeur);
		majTempsActionParFatigue(c);
	}

	/**
	 * Défini la valeur totale de la caractéristique donnée
	 *
	 * @param c
	 * @param valeur
	 */
	public void setTotal(Carac c, int valeur) {
		get(c).setTotal(valeur);
	}

	/**
	 *
	 * @param c
	 * @return la caracteristique de type c
	 */
	public Caracteristique get(Carac c) {
		switch (c) {
			case VITALITE:
				return listCaracteristiques[0];
			case TEMPSACTION:
				return listCaracteristiques[1];
			case TEMPSSUPP:
				return listCaracteristiques[2];
			case FATIGUE:
				return listCaracteristiques[3];
			case VITESSEACTION:
				return listCaracteristiques[4];
			case INITIATIVE:
				return listCaracteristiques[5];
			default:
				throw new Error("Enumeration non gérée.");
		}
	}

	/**
	 * hashCode
	 *
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(listCaracteristiques);
		return result;
	}

	/**
	 * equals en fonction de listCaracteristique
	 *
	 * @return
	 */
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
		CaracteristiquePhysique other = (CaracteristiquePhysique) obj;
		return Arrays.equals(listCaracteristiques, other.listCaracteristiques);
	}

}
