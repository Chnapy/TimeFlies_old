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

	public CaracteristiquePhysique(int vita,
			int tAction,
			int tSup,
			int fat,
			int vAction) {

		listCaracteristiques = new Caracteristique[]{
			new Vitalite(vita),
			new TempsAction(tAction),
			new TempsSup(tSup),
			new Fatigue(fat),
			new VitesseAction(vAction)
		};

	}

	public void add(Carac c, int gain) {
		switch (c) {
			case VITALITE:
				listCaracteristiques[0].add(gain);
				break;
			case TEMPSACTION:
				listCaracteristiques[1].add(gain);
				break;
			case TEMPSSUP:
				listCaracteristiques[2].add(gain);
				break;
			case FATIGUE:
				listCaracteristiques[3].add(gain);
				break;
			case VITESSEACTION:
				listCaracteristiques[4].add(gain);
				break;
			default:
				throw new Error("Enumeration non gérée.");
		}
	}

	public void supp(Carac c, int perte) {
		switch (c) {
			case VITALITE:
				listCaracteristiques[0].supp(perte);
				break;
			case TEMPSACTION:
				listCaracteristiques[1].supp(perte);
				break;
			case TEMPSSUP:
				listCaracteristiques[2].supp(perte);
				break;
			case FATIGUE:
				listCaracteristiques[3].supp(perte);
				break;
			case VITESSEACTION:
				listCaracteristiques[4].supp(perte);
				break;
			default:
				throw new Error("Enumeration non gérée.");
		}
	}

}
