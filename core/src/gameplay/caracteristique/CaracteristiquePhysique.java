/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

import gameplay.sort.Carac;

/**
 * CaracteristiquePhysique.java
 * Gère l'ensemble des caractéristiques physiques de l'entité.
 *
 */
public class CaracteristiquePhysique {

	private Caracteristique[] listCaracteristiques;

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
			new VitesseAction(vActionTotal, vActionActu)
		};

	}

	public void add(Carac c, int gain) {
		getCaracteristique(c).supp(gain);
	}

	public void supp(Carac c, int perte) {
		getCaracteristique(c).supp(perte);
	}

	public void setActu(Carac c, int valeur) {
		getCaracteristique(c).setActu(valeur);
	}

	public void setTotal(Carac c, int valeur) {
		getCaracteristique(c).setTotal(valeur);
	}

	private Caracteristique getCaracteristique(Carac c) {
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
			default:
				throw new Error("Enumeration non gérée.");
		}
	}

}
