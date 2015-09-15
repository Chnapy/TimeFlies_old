/*
 * 
 * 
 * 
 */
package gameplay.sort.base;

import com.badlogic.gdx.utils.Array;
import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.effet.placement.Rotation;
import gameplay.entite.Entite;
import gameplay.entite.EntiteActive;
import gameplay.map.Tuile;
import gameplay.sort.Niveau;
import gameplay.sort.SortActif;
import gameplay.sort.zone.Carre;
import gameplay.sort.zone.ZoneAction;
import general.Orientation;

/**
 * Orienter.java
 *
 */
public class Orienter extends SortActif {

	private static final int TEMPS_ACTION = 0;

	public Orienter() {
		super("Orienter", "Oriente l'entité", new Niveau(0),
				new Effet[]{
					new Effet(
							new Array<Declencheur>(
									new Declencheur[]{
										new Rotation()
									}
							)
					)
				},
				new ZoneAction(new Carre(0, true)),
				new ZoneAction(new Carre(0, true)),
				0,//todo
				TEMPS_ACTION);
	}

	@Override
	public void lancerSort(Entite cible, Tuile cible2, EntiteActive lanceur, Orientation oriAttaque, boolean critique) {
//		System.out.println(cible.getNom());
		super.lancerSort(cible, cible2, lanceur, oriAttaque, critique);
		lanceur.notifierObserveurs(new Object[]{
			lanceur.getCaracSpatiale().getOrientation(),
			TEMPS_ACTION
		});
	}

}
