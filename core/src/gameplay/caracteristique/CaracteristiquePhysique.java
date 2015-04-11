/*
 * 
 * 
 * 
 */
package gameplay.caracteristique;

/**
 * CaracteristiquePhysique.java
 * Gère l'ensemble des caractéristiques physiques de l'entité.
 *
 */
public class CaracteristiquePhysique {

	private Vitalite vitalite;
	private TempsAction tempsAction;
	private TempsSup tempsSup;
	private Fatigue fatigue;
	private VitesseAction vitesseAction;

	public CaracteristiquePhysique(int vita,
			int tAction,
			int tSup,
			int fat,
			int vAction) {

		vitalite = new Vitalite(vita);
		tempsAction = new TempsAction(tAction);
		tempsSup = new TempsSup(tSup);
		fatigue = new Fatigue(fat);
		vitesseAction = new VitesseAction(vAction);

	}

}
