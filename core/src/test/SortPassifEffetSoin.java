/*
 * 
 * 
 * 
 */
package test;

import gameplay.caracteristique.Carac;
import gameplay.effet.Balus;
import gameplay.effet.Declenchable;
import gameplay.effet.Declencheur;
import gameplay.effet.Effet;
import gameplay.entite.Entite;
import gameplay.sort.Niveau;
import gameplay.sort.SortPassifEffets;
import general.Orientation;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.vChatBox;

/**
 * SortPassifEffetSoin.java
 *
 */
public class SortPassifEffetSoin extends SortPassifEffets {

	private final static String NOM = "SortPassifEffetSoin";
	private final static String DESCRIPTION = "Lors de la reception de degats >30, l'entite recois 20 soin.";
	private final static int INDEX = 2;

	public SortPassifEffetSoin() {
		super(NOM,
				DESCRIPTION,
				new Niveau(0),
				new Effet[]{
					new Effet(new Declencheur[]{
						new Balus(Carac.VITALITE, 20)
					})
				},
				new Declenchable[]{
					new DeclenchablePourSortPassif()
				},
				INDEX);
	}

	@Override
	protected void actionApplyEffect(Entite lanceur, Entite cible, boolean isAvant, boolean ccritique) {
		if (isAvant) {
			return;
		}
		vChatBox.chatCombatPrint(getNom() + " declenche !", vChatText.ChatTextType.COMBAT);
		cible.recoitSort(getTabEffets(), lanceur, Orientation.NORD, ccritique);
	}

}
