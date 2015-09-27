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
import gameplay.envoutement.EnvoutementEffets;
import gameplay.map.Tuile;
import general.Orientation;
import vue.hud.chatbox.chattext.vChatText;
import vue.hud.chatbox.vChatBox;

/**
 * EnvoutementQuiSeDeclenche.java
 *
 */
public class EnvoutementQuiSeDeclenche extends EnvoutementEffets {

	public EnvoutementQuiSeDeclenche() {
		super(
				"EnvoutementQuiSeDeclenche",
				3,
				new Declenchable[]{
					new DeclenchablePourEnvoutement()
				},
				new Effet[]{
					new Effet(new Declencheur[]{
						new Balus(Carac.VITALITE, 60)
					})
				}
		);
	}

	@Override
	public void actionDebutTour() {
	}

	@Override
	public void actionFinTour() {
	}

	@Override
	public void actionDebutTourGlobal() {
	}

	@Override
	public void actionFinTourGlobal() {
	}

	@Override
	public void actionDebutEnvoutement() {
		vChatBox.chatCombatPrint(getCible().getNom() + " recoit l'envoutement effet " + getNom(), vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void actionFinEnvoutement() {
		vChatBox.chatCombatPrint(getCible().getNom() + " perd l'envoutement effet " + getNom(), vChatText.ChatTextType.COMBAT);
	}

	@Override
	public void actionLancerEntite(Orientation oriLanceur, boolean ccritique) {
	}

	@Override
	public void lancerTuile(Tuile cible, Entite lanceur, Orientation oriLanceur, boolean ccritique) {
	}

	@Override
	protected void actionApplyEffect(Entite lanceur, boolean isAvant, boolean ccritique) {
		if (isAvant) {
			return;
		}
		vChatBox.chatCombatPrint(getNom() + " declenche !", vChatText.ChatTextType.COMBAT);
		getCible().recoitSort(getListEffets(), lanceur, Orientation.NORD, ccritique);
	}

}
