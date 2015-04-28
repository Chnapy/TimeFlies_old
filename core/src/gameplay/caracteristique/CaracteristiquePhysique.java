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

	private Caracteristique[] listCaracteristiques;

	/**
	 *
	 * @param vitaTotal
	 * @param vitaActu
	 * @param tActionTotal
	 * @param tActionActu
	 * @param tSupTotal
	 * @param tSupActu
	 * @param fatTotal
	 * @param fatActu
	 * @param vActionTotal
	 * @param vActionActu
	 */
	public CaracteristiquePhysique(int vitaTotal, int vitaActu,
			int tActionTotal, int tActionActu,
			int tSupTotal, int tSupActu,
			int fatTotal, int fatActu,
			int vActionTotal, int vActionActu) {

		listCaracteristiques = new Caracteristique[]{
			new Vitalite(vitaTotal, vitaActu),
			new TempsAction(tActionTotal, tActionActu),
			new TempsSup(tSupTotal, tSupActu),
			new Fatigue(fatTotal, fatActu),
			new VitesseAction(vActionTotal, vActionActu),
			new Initiative(-1)
		};

	}

	/**
	 *
	 * @param c
	 * @param gain
	 */
	public void add(Carac c, int gain) {
		getCaracteristique(c).supp(gain);
	}

	/**
	 *
	 * @param c
	 * @param perte
	 */
	public void supp(Carac c, int perte) {
		getCaracteristique(c).supp(perte);
	}

	/**
	 *
	 * @param c
	 * @param valeur
	 */
	public void setActu(Carac c, int valeur) {
		getCaracteristique(c).setActu(valeur);
	}

	/**
	 *
	 * @param c
	 * @param valeur
	 */
	public void setTotal(Carac c, int valeur) {
		getCaracteristique(c).setTotal(valeur);
	}

	public Caracteristique getCaracteristique(Carac c) {
		switch (c) {
			case VITALITE:
				return listCaracteristiques[0];
			case TEMPSACTION:
				return listCaracteristiques[1];
			case TEMPSSUP:
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
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(listCaracteristiques);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristiquePhysique other = (CaracteristiquePhysique) obj;
		if (!Arrays.equals(listCaracteristiques, other.listCaracteristiques))
			return false;
		return true;
	}

}
