/*
 * 
 * 
 * 
 */
package vue.hud.tabcarac;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import controleur.ControleurPrincipal;
import gameplay.caracteristique.Carac;
import gameplay.entite.EntiteActive;
import general.Tourable;
import general.TypeDonnee;
import java.util.HashMap;
import static test.MainTest.DF;
import vue.hud.Bloc;

/**
 * TableauCarac.java
 *
 */
public class TableauCarac extends Bloc implements Tourable {

	//Position et taille de la timeline
	private static final int X = 20;
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final int Y = 350;

	private final HashMap<TypeDonnee, Donnee> donneesList;

	private String temp;

	public TableauCarac(AssetManager manager) {
		super("Donnees", WIDTH, HEIGHT, manager);
		setPosition(X, Y);

		donneesList = new HashMap();
		donneesList.put(TypeDonnee.NOM, new Donnee(TypeDonnee.NOM, manager));
		donneesList.put(TypeDonnee.VIE, new Donnee(TypeDonnee.VIE, manager));
		donneesList.put(TypeDonnee.TEMPSACTION, new Donnee(TypeDonnee.TEMPSACTION, manager));
		donneesList.put(TypeDonnee.TEMPSSUPP, new Donnee(TypeDonnee.TEMPSSUPP, manager));
		donneesList.put(TypeDonnee.FATIGUE, new Donnee(TypeDonnee.FATIGUE, manager));
		donneesList.put(TypeDonnee.VITESSEACTION, new Donnee(TypeDonnee.VITESSEACTION, manager));

		align(Align.top);
		padLeft(10);
		padRight(10);
		defaults().padTop(10).left();
		add(donneesList.get(TypeDonnee.NOM)).expandX().fillX().row();
		add(donneesList.get(TypeDonnee.VIE)).expandX().fillX().row();
		add(donneesList.get(TypeDonnee.TEMPSACTION)).width(WIDTH / 2 - getPadLeft());
		add(donneesList.get(TypeDonnee.TEMPSSUPP)).width(WIDTH / 2 - getPadRight()).row();
		add(donneesList.get(TypeDonnee.FATIGUE)).width(WIDTH / 2 - getPadLeft());
		add(donneesList.get(TypeDonnee.VITESSEACTION)).width(WIDTH / 2 - getPadRight()).row();

	}

	@Override
	protected void render(Batch batch, float parentAlpha) {
	}

	@Override
	public void nouveauTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		getTitleLabel().setText("Donnees - " + entiteEnCours.getNom());
		donneesList.get(TypeDonnee.NOM).setText(entiteEnCours.getNom() + " lvl " + entiteEnCours.getNiveauSymbol());
		donneesList.get(TypeDonnee.VIE).setText(
				entiteEnCours.getCaracPhysique().getCaracteristique(Carac.VITALITE).getActu()
				+ " / "
				+ entiteEnCours.getCaracPhysique().getCaracteristique(Carac.VITALITE).getTotal()
		);
		donneesList.get(TypeDonnee.FATIGUE).setText(
				entiteEnCours.getCaracPhysique().getCaracteristique(Carac.FATIGUE).getActu()
				+ " %"
		);
		donneesList.get(TypeDonnee.VITESSEACTION).setText(
				entiteEnCours.getCaracPhysique().getCaracteristique(Carac.VITESSEACTION).getActu()
				+ " %"
		);
		enTour(controleur, entiteEnCours, objs);
	}

	@Override
	public void finTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		getTitleLabel().setText("Donnees");
		donneesList.forEach((key, donnee) -> {
			donnee.setText("-");
		});
		temp = null;
	}

	@Override
	public void enTour(ControleurPrincipal controleur, EntiteActive entiteEnCours, Object... objs) {
		temp = DF.format((float) entiteEnCours.getCaracPhysique().getCaracteristique(Carac.TEMPSACTION).getActu() / 1000)
				+ "s / "
				+ DF.format((float) entiteEnCours.getCaracPhysique().getCaracteristique(Carac.TEMPSACTION).getTotal() / 1000)
				+ "s";
		if (!donneesList.get(TypeDonnee.TEMPSACTION).getText().equals(temp)) {
			donneesList.get(TypeDonnee.TEMPSACTION).setText(temp);
		}
		temp = DF.format((float) entiteEnCours.getCaracPhysique().getCaracteristique(Carac.TEMPSSUPP).getActu() / 1000)
				+ "s / "
				+ DF.format((float) entiteEnCours.getCaracPhysique().getCaracteristique(Carac.TEMPSSUPP).getTotal() / 1000)
				+ "s";
		if (!donneesList.get(TypeDonnee.TEMPSSUPP).getText().equals(temp)) {
			donneesList.get(TypeDonnee.TEMPSSUPP).setText(temp);
		}
	}

}
